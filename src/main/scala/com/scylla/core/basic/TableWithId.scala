package com.scylla.core.basic

import slick.lifted.Rep

trait TableWithId {
  def id: Rep[Long]
}
