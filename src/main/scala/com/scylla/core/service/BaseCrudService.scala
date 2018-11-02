package com.scylla.core.service

import com.scylla.core.basic.EntityWithId
import com.scylla.core.dao.BaseCRUDDao
import scala.concurrent.Future

trait BaseCrudService[T <: EntityWithId] extends BaseCRUDDao[T] {

  import baseTable.driver.api._

  db.run(createSchema)

  def add(entity: T): Future[Long] = db.run(insert(entity))

  def edit(entity: T): Future[Int] = {
    if (entity.id.isDefined) {
      db.run(update(entity))
    } else {
      db.run(DBIO.successful(0))
    }
  }

  def findById(id: Long): Future[Option[T]] = db.run(selectById(id))

  def remove(id: Long): Future[Int] = db.run(delete(id))

  def all: Future[Seq[T]] = db.run(selectAll)

  def addOrEdit(entity: T): Future[Option[Long]] = db.run(insertOrUpdate(entity))

  def batchAdd(entities: Seq[T]): Future[Option[Int]] = db.run(batchInsert(entities))

}
