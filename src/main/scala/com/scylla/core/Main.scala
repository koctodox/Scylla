package com.scylla.core

import akka.http.scaladsl.Http
import com.scylla.core.commons.CoreStaticActorRefs._
import com.scylla.core.actor.JWTSecretKeyGenerator._
import com.scylla.core.basic.BaseSystem
import com.scylla.route.HttpRouter
import scala.concurrent.duration._

object Main extends App with BaseSystem {
  system.scheduler.schedule(initialDelay = 0.seconds, interval = 3.hour, receiver = jwtSecretKeyActor, message = GenerateNewSecretKey)

  Http().bindAndHandle(HttpRouter().routes, httpInterface, httpPort)
  logger.info(s"Server online at 'http://$httpInterface:$httpPort'")
}
