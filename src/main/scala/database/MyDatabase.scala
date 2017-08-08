package database

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.dsl._
import model.UsersModel
import connector.Connector._
import entity.User

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object Test extends App {
  val user = User(1, "Dorina", "dor@yahoo.com")
  val f = ProductionDB.saveOrUpdate(user)
  val f2 = f andThen {
    case Success(v) =>
      Thread.sleep(10000)
      println("The program waited patiently for this callback to finish.")
    case Failure(e) =>
      println(e)
  }
  println("Done")
}

object Defaults {
  val connector = ContactPoint.local.keySpace("my_keyspace")
}

/**
  * This is our Database object that wraps our one existing table,
  * giving the ability to receive different connectors
  * for example: One for production and other for testing
  *
  * one object for each table (defined in model package)
  */
class MyDatabase (override val connector: CassandraConnection)
  extends Database[MyDatabase](connector) {
      object UsersModel extends UsersModel with connector.Connector

  def saveOrUpdate(user: User): Future[ResultSet] = {
     for {
       user <- UsersModel.store(user).future()
     } yield user
  }
}

object ProductionDB extends MyDatabase(connector)

