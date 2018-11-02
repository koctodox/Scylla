package com.scylla.core

import com.scylla.core.basic._
import spray.json.RootJsonFormat

package object models {

  case class Result(
                     code: Int
                   ) extends Marshallable

  object Result extends Marshaller[Result] {
    override implicit val formatter: RootJsonFormat[Result] = jsonFormat1(Result.apply)
  }

  case class CallBack(
                       status: Int,
                       transId: Int,
                       message: String,
                       cardNumber: Option[String] = None,
                       traceNumber: Option[String] = None,
                       factorNumber: Option[String] = None,
                       mobile: Option[String] = None,
                       description: Option[String] = None
                     ) extends Marshallable

  object CallBack extends Marshaller[CallBack] {
    override implicit val formatter: RootJsonFormat[CallBack] = jsonFormat8(CallBack.apply)
  }

  case class InterUserToken(
                             uuid: String,
                             role: Option[Int] = None
                           ) extends Marshallable

  object InterUserToken extends Marshaller[InterUserToken] {
    override implicit val formatter: RootJsonFormat[InterUserToken] = jsonFormat2(InterUserToken.apply)
  }

}
