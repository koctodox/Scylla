package com.scylla.core.route

import akka.http.scaladsl.model.{HttpEntity, HttpResponse}
import com.scylla.core.models.Result
import com.scylla.commons.MessageCodes._
import com.scylla.core.basic.Marshallable
import spray.json._

trait Results {

  def response(body: String, status: Int): HttpResponse = {
    HttpResponse(status , entity = HttpEntity(body))
  }

  def response[T <: Marshallable](obj: T, status: Int = OK_200)(implicit writer: JsonWriter[T]): HttpResponse = {
    response(obj.toJson.toString, status)
  }

  def okResponse: HttpResponse = {
    response(Result(OK_200))
  }

  def response(messageCodes: Int): HttpResponse = {
    response(Result(messageCodes), BAD_REQUEST_400)
  }

}
