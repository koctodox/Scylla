package com.scylla.core.route

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import com.scylla.core.commons.HardCodes

class OAuthAPI(path0: String, path1: String = "") extends BaseAPI(path0, path1)(CorsSettings.defaultSettings) with HardCodes {

  def POST[R](func: (R, String) => ToResponseMarshallable)(implicit fromRequestUnmarshaller: FromRequestUnmarshaller[R]): Route = {
    simplePost {
      headerValueByName(OAUTH_HEADER) { token =>
        entity(as[R]) { entity =>
          complete {
            func(entity, token)
          }
        }
      }
    }
  }

  def GET(func: String => ToResponseMarshallable): Route = {
    simpleGet {
      headerValueByName(OAUTH_HEADER) { token =>
        complete {
          func(token)
        }
      }
    }
  }

}
