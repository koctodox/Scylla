package com.scylla.route

import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.scylla.core.route.API
import com.scylla.core.util.Utilities

case class HttpRouter() extends Utilities {

  def index: Route = new API("") GET complete(okResponse)

  def routes: server.Route = index
}
