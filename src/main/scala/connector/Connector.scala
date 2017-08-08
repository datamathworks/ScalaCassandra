package connector

import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoint, ContactPoints}
import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._

object Connector {
  private val config = ConfigFactory.load()

  private val hosts = List("127.0.0.1").toBuffer
  private val keyspace = "test5"
  private val username = "cassandra"
  private val password = "cassandra"

//  host = ["127.0.0.1"]
//  keyspace = "test"
//  username = "cassandra"
//  password = "cassandra"
  /**
    * Create a connector with the ability to connects to
    * multiple hosts in a secured cluster
    */
  lazy val connector: CassandraConnection = ContactPoints(hosts)
    .withClusterBuilder(_.withCredentials(username, password))
    .keySpace(keyspace)

  /**
    * Create an embedded connector, testing purposes only
    */
  lazy val testConnector: CassandraConnection = ContactPoint.embedded.noHeartbeat().keySpace("my_app_test")
}
