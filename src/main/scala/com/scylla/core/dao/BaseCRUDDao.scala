package com.scylla.core.dao

import com.scylla.core.basic.EntityWithId

trait BaseCRUDDao[T <: EntityWithId] extends BaseDao[T] {
  import baseTable.driver.api._

  def createSchema = query.schema.create

  def selectByIdQuery (id: Long) = query.filter(_.id === id)

  def insert(entity: T) = query returning query.map(_.id) += entity

  def update(entity: T) = selectByIdQuery(entity.id.get).update(entity)

  def selectById(id: Long) = selectByIdQuery(id).result.headOption

  def delete(id: Long) = selectByIdQuery(id).delete

  def selectAll = query.result

  def insertOrUpdate(entity: T) = {
    entity.id match {
      case Some(id) => query.insertOrUpdate(entity).map(_ => Some(id))
      case None => query.returning(query.map(_.id)).insertOrUpdate(entity)
    }
  }

  def batchInsert(entities: Seq[T]) = query ++= entities

}
