package openaf.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.StrictLogging

import scala.util.{Failure, Success}

object Server extends StrictLogging {
  def main(args: Array[String]): Unit = {
    logger.info("Starting...")

    implicit val actorSystem = ActorSystem("server")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = actorSystem.dispatcher

    val route =
      path("") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Hello"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    bindingFuture.onComplete {
      case Success(binding) =>
        logger.info(s"Server started at ${binding.localAddress}")
      case Failure(e) =>
        logger.error("Server couldn't be started", e)
        actorSystem.terminate()
    }
  }
}
