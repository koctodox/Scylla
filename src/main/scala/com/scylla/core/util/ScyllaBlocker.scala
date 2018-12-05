package com.scylla.core.util

import com.scylla.core.commons.Config

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.Try

object ScyllaBlocker extends Config {
  implicit class RichFuture[A](val future: Future[A]) extends AnyVal {
    def blockResult(atMost: Duration): A = Await.result(future, atMost)
    def tryResult(atMost: Duration): Try[A] = Try(Await.result(future, atMost))
  }
}
