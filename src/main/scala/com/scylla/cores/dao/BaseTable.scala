package com.scylla.cores.dao

import com.scylla.cores.basics.TableWithId
import slick.jdbc.JdbcProfile

trait BaseTable[A] {
  val driver: JdbcProfile
  import driver.api._
  type TableDef <: Table[A] with TableWithId
  def query: TableQuery[TableDef]
}
