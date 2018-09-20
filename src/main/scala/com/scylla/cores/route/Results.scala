package com.scylla.cores.route

import akka.http.scaladsl.model.{HttpEntity, HttpResponse}
import com.scylla.cores.models.Result
import com.scylla.commons.MessageCodes._
import com.scylla.cores.basics.Marshallable
import spray.json._

trait Results {

  def response(body: String, status: Int): HttpResponse = {
    HttpResponse(status , entity = HttpEntity(body))
  }

  def response[T <: Marshallable](obj: T, status: Int = OK_200)(implicit writer: JsonWriter[T]): HttpResponse = {
    response(obj.toJson.toString, status)
  }

  def response: HttpResponse = {
    response(Result(OK_200))
  }

  def response(messageCodes: Int): HttpResponse = {
    response(Result(messageCodes), BAD_REQUEST_400)
  }

}
