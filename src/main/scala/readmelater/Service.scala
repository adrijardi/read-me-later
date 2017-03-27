package readmelater

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Future
import scala.util.Random

/**
  * Main class of the application where the routes are defined
  */
object Service extends App with StrictLogging {

  implicit val system = ActorSystem("read-me-later-actor-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val repo = new DynamoDbRepository

  val route: Route =
    pathEndOrSingleSlash {
      get(
        complete {
          repo.count(ec).flatMap { count =>
            val random = Random.nextInt(count)
            val retrieve: Future[Option[String]] = repo.retrieve(random)(ec).map(_.map(_.url))
            retrieve
          }
        }
      )
    } ~
    path("add" / Segment) { str =>
      get(
        complete(repo.insert(ReadItem(str))(ec).map(_.toString))
      )
    }

  private val port = 8080

  Http().bindAndHandle(route, "0.0.0.0", port)

  logger.info(s"Application started on port $port")
}
