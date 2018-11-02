package com.scylla.core.dao

import com.scylla.core.basic.{BaseSystem, EntityWithId}

trait BaseDao[A <: EntityWithId] extends BaseSystem {
  val baseTable: BaseTable[A]
  import baseTable.driver.api._
  protected val query: TableQuery[baseTable.TableDef] = baseTable.query
  protected val db: baseTable.driver.backend.DatabaseDef = Database.forConfig(dbPath)
}
