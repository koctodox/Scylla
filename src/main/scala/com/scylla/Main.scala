package com.scylla

import akka.http.scaladsl.Http
import com.scylla.cores.basics.BaseSystem
import com.scylla.route.HttpRouter

/*By ```hajjijo```
just a geek*/

object Main extends App with BaseSystem {

  Http().bindAndHandle(HttpRouter().routes, httpInterface, httpPort)
  logger.info(s"Server online at 'http://$httpInterface:$httpPort'")
}
