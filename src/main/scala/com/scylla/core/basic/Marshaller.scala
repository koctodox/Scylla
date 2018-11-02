package com.scylla.core.basic

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait Marshaller[T <: Marshallable] extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val formatter: RootJsonFormat[T]
}
