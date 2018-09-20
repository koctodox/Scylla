package com.scylla.commons

object MessageCodes extends Enumeration {
  type MessageCodes = Value

  val OK_200 = 200
  val PROBLEM_WITH_SERVER_500 = 500
  val BAD_REQUEST_400 = 400
  val TOKEN_IS_EMPTY_1 = 1
  val INVALID_JSON_FORMAT_2 = 2
  val TOKEN_IS_INVALID_3 = 3
}
