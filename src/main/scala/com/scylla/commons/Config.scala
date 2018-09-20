package com.scylla.commons

import com.typesafe.config
import com.typesafe.config.ConfigFactory

trait Config {

  val conf: config.Config = ConfigFactory.load
  val namespace = "com.scylla"
  def getEnv : String = conf getString s"$namespace.env"

  def prefix = s"$namespace.$getEnv"

  val httpInterface: String = conf getString s"$prefix.http.interface"
  val httpPort: Int = conf getInt s"$prefix.http.port"

  //TODO: this is just a simple path
  val dbPath: String = s"$prefix.db"

  //TODO: move to hard-code-configurations
  val oneDay2Sec_86400: Int = 86400

  //TODO: change it with actor-system-scheduler ...
  val jwtSecretKey: String = "jwt-secret-key"
}
