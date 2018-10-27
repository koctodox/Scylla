package com.scylla.core.basic

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.scylla.commons.Config
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.ExecutionContextExecutor

trait BaseSystem extends Config with LazyLogging {
  implicit val system: ActorSystem = ActorSystem("BasicSystem")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

}
