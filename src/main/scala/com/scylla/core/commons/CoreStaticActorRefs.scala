package com.scylla.core.commons

import akka.actor.ActorRef
import com.scylla.core.Main.system
import com.scylla.core.actor.JWTSecretKeyGenerator

object CoreStaticActorRefs {
  val jwtSecretKeyActor: ActorRef = system.actorOf(JWTSecretKeyGenerator.props.withDispatcher("singleThreadDigree-dispatcher"), name = "jwtSecretKeyActor")

}
