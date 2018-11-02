package com.scylla.core.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

class API(path0: String, path1: String = "") {

  def POST(remain: Route): Route = {
    cors() {
      post {
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
    }
  }

  def GET(remain: Route): Route = {
    cors() {
      get {
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
    }
  }

}
