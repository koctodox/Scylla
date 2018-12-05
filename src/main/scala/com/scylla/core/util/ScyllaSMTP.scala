package com.scylla.core.util

import java.util.Date

import com.scylla.core.commons.Config
import javax.mail._
import javax.mail.internet.{InternetAddress, MimeMessage}

case class ScyllaSMTP() extends Config {

  def sendMail(address: String, title: String, body: String): Boolean = {
    try {
      val msg = new MimeMessage(getSession)
      msg.setFrom(new InternetAddress(emailAddress))
      msg.setRecipients(Message.RecipientType.TO, address)
      msg.setSubject(title)
      msg.setSentDate(new Date())
      if (body.contains("<") && body.contains(">")) {
        msg.setContent(body, "text/html;charset=utf-8")
      } else {
        msg.setText(body)
      }
      Transport.send(msg)
      true
    } catch {
      case th: Throwable => th.printStackTrace()
        false
    }
  }

  private def getSession: Session = {
    val SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"
    val props = System.getProperties
    props.setProperty("mail.smtp.host", "sh3.hilvm.de")
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY)
    props.setProperty("mail.smtp.socketFactory.fallback", "false")
    props.setProperty("mail.smtp.port", "465")
    props.put("mail.smtp.auth", "true")
    val authenticator: Authenticator = new Authenticator() {
      override protected def getPasswordAuthentication: PasswordAuthentication = {
        new PasswordAuthentication(emailAddress, emailPass)
      }
    }
    Session.getDefaultInstance(props, authenticator)
  }

}
