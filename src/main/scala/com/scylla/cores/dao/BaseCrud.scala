package com.scylla.cores.dao

import com.scylla.cores.basics.EntityWithId
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.github.tototoshi.slick.PostgresJodaSupport._

//TODO: refactor Implicits convention from global to akka custom dispatcher

trait BaseCrud[T <: EntityWithId] extends BaseDao[T] {
  import baseTable.driver.api._

  db.run(query.schema.create)

  def selectByIdQuery (id: Long): Query[baseTable.TableDef, T, Seq] = query.filter(_.id === id)

  def insert(entity: T): Future[Long] = {
    db.run(query returning query.map(_.id) += entity)
  }

  def update(entity: T): Future[Int] = {
    entity.id match {
      case Some(id) => db.run(selectByIdQuery(id).update(entity))
      case None => db.run(DBIO.successful(0))
    }
  }

  def selectById(id: Long): Future[Option[T]] = {
    db.run(selectByIdQuery(id).result.headOption)
  }

  def delete(id: Long): Future[Int] = {
    db.run(selectByIdQuery(id).delete)
  }

  def selectAll: Future[Seq[T]] = {
    db.run(query.result)
  }

  def insertOrUpdate(entity: T): Future[Option[Long]] = {
    entity.id match {
      case Some(id) => db.run(query.insertOrUpdate(entity).map(_ => Some(id)))
      case None => db.run(query.returning(query.map(_.id)).insertOrUpdate(entity))
    }
  }

  def batchInsert(entities: Seq[T]): Future[Option[Int]] = db.run(query ++= entities)

}
