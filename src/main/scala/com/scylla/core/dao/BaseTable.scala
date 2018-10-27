package com.scylla.core.dao

import com.scylla.core.basic.TableWithId
import slick.jdbc.JdbcProfile

trait BaseTable[A] {
  val driver: JdbcProfile
  import driver.api._
  type TableDef <: Table[A] with TableWithId
  def query: TableQuery[TableDef]
}
