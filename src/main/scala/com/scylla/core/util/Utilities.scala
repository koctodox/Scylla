package com.scylla.core.util

import java.util.UUID

import com.scylla.commons.MessageCodes._
import com.scylla.core.models.CallBack
import com.scylla.core.route.Results

trait Utilities extends Results {
  def string2UUID(str: String): Either[Int, UUID] = {
    try {
      Right(UUID.fromString(str))
    } catch {
      case _: IllegalArgumentException =>
        Left(INVALID_JSON_FORMAT_2)
    }
  }

  def payCallBackReq2CallBack(str: String): CallBack = {
    import spray.json._
    import DefaultJsonProtocol._

    val immutableMap: Map[String, JsValue] = str.split("&").toList map { str =>
      str.split("=").toList
    } filter (_.size == 2) map { tuples =>
      if (tuples.head.equals("status")) {
        (tuples.head, tuples.tail.head.toInt.toJson)
      } else if(tuples.head.equals("transId")) {
        (tuples.head, tuples.tail.head.toInt.toJson)
      } else {
        (tuples.head, tuples.tail.head.toJson)
      }
    } toMap

    immutableMap.toJson.convertTo[CallBack]
  }

}
