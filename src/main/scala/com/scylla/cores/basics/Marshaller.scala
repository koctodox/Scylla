package com.scylla.cores.basics

import spray.json.RootJsonFormat

trait Marshaller[T <: Marshallable] {
  implicit val formatter: RootJsonFormat[T]
}
