package com.scylla.cores.dao

import com.scylla.commons.Config
import com.scylla.cores.basics.EntityWithId

trait BaseDao[A <: EntityWithId] extends Config {
  val baseTable: BaseTable[A]
  import baseTable.driver.api._
  protected val query: TableQuery[baseTable.TableDef] = baseTable.query
  protected val db: baseTable.driver.backend.DatabaseDef = Database.forConfig(dbPath)
}
