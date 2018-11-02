package com.scylla.core.actor

import akka.actor.Props
import com.scylla.core.actor.JWTSecretKeyGenerator._

case class JWTSecretKeyGenerator() extends ActorsMother {
  var secretKey: String = ""
  override def receive: Receive = {
    case GenerateNewSecretKey =>
      secretKey = encryptionLib.generateRandomJWTSecretKey
    case GetSecretKey =>
      sender ! secretKey
    case _ =>
      log.warning(s"[JWTSecretKeyGenerator] received anonymous message!!!")
  }
}

object JWTSecretKeyGenerator {
  def props: Props = Props[JWTSecretKeyGenerator]

  case object GenerateNewSecretKey

  case object GetSecretKey
}