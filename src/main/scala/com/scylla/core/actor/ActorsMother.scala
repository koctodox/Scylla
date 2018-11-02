package com.scylla.core.actor

import akka.actor.{Actor, ActorLogging}
import com.scylla.core.util.ScyllaEncryptor

trait ActorsMother extends Actor with ActorLogging {
  def encryptionLib: ScyllaEncryptor = ScyllaEncryptor()
}
