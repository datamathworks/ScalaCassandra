package model //contains a class for each table

import java.util.UUID

import com.outworkers.phantom.dsl._
import entity.User


import scala.concurrent.Future


abstract class UsersModel extends Table[UsersModel, User] {

  override def tableName: String = "users"

  object id extends IntColumn with PartitionKey
  object name extends StringColumn
  object email extends StringColumn


//  def create(user: User): Future[UUID] = {
//    store(user)
//      .consistencyLevel_=(ConsistencyLevel.ALL)
//      .future()
//      .map(_ => user.id)
//  }


//  def getById(id: UUID): Future[Option[User]] = {
//    select.where(_.id eqs id).one()
//  }

}
