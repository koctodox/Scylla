package com.scylla.core.basic

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.scylla.commons.Config
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.duration._
import scala.concurrent.ExecutionContextExecutor

trait BaseSystem extends Config with LazyLogging {
  implicit val system: ActorSystem = ActorSystem("BasicSystem")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val timeout: Timeout = Timeout(3.seconds)

}
