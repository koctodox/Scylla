package com.scylla.core.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings

abstract class BaseAPI(path0: String, path1: String)(corsSettings: CorsSettings) {

  def simplePost(remain: Route): Route = {
    corsPost {
      setPath {
        remain
      }
    }
  }

  def simpleGet(remain: Route): Route = {
    corsGet {
      setPath {
        remain
      }
    }
  }

  def setPath(remain: Route): Route = {
    path1.trim.isEmpty match {
      case false =>
        pathPrefix(path0) {
          path(path1) {
            remain
          }
        }
      case true =>
        path(path0) {
          remain
        }
    }
  }

  def corsPost(remain: Route): Route = {
    cors(corsSettings) {
      post {
        remain
      }
    }
  }

  def corsGet(remain: Route): Route = {
    cors(corsSettings) {
      get {
        remain
      }
    }
  }

}
