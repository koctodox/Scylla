package com.scylla.core.route

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import com.scylla.core.commons.HardCodes

class API(path0: String, path1: String = "") extends BaseAPI(path0, path1)(CorsSettings.defaultSettings) with HardCodes {

  def POST[R](body: R => ToResponseMarshallable)(implicit fromRequestUnmarshaller: FromRequestUnmarshaller[R]): Route = {
    simplePost {
      entity(as[R]) { req: R =>
        complete {
          body(req)
        }
      }
    }
  }

  def GET(remain: ToResponseMarshallable): Route = {
    simpleGet {
      complete(remain)
    }
  }

}
