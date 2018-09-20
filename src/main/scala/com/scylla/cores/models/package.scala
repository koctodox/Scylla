package com.scylla.cores

import com.scylla.cores.basics.{Marshallable, Marshaller}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

package object models {

  case class Result(
                     code: Int
                   ) extends Marshallable

  object Result extends Marshaller[Result] {
    override implicit val formatter: RootJsonFormat[Result] = jsonFormat1(Result.apply)
  }

  case class InterUserToken(
                             uuid: String,
                             role: Option[Int] = None
                           ) extends Marshallable

  object InterUserToken extends Marshaller[InterUserToken] {
    override implicit val formatter: RootJsonFormat[InterUserToken] = jsonFormat2(InterUserToken.apply)
  }

}
