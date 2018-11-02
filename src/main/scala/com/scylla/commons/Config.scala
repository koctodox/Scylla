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

  val dbPath: String = s"$prefix.db"

  val emailAddress: String = s"$prefix.email.address"
  val emailPass: String = s"$prefix.email.pass"

  val ONEDAYSECS_86400: Int = 86400
}
