package com.scylla.cores.basics

import slick.lifted.Rep

trait TableWithId {
  def id: Rep[Long]
}
