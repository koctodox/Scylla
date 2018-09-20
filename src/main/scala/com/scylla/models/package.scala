package com.scylla

import com.scylla.cores.basics.{Marshaller, Marshallable}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

package object models {

  case class User(username: String, password: String) extends Marshallable

  object User extends Marshaller[User] {
    override implicit val formatter: RootJsonFormat[User] = jsonFormat2(User.apply)
  }

}
