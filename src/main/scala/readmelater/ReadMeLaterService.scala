package readmelater

import readmelater.Service.{ec, repo}

import scala.concurrent.Future
import scala.language.higherKinds
import scala.util.Random

/**
  * Created by adrij_000 on 25/03/2017.
  */
class ReadMeLaterService[T[_] : IO : Monad](repo: T) {

  def store(read: ReadItem) = ???

  def retrieveRandom: T[Option[String]] = {
    repo.count(ec).flatMap { count =>
      val random = Random.nextInt(count)
      val retrieve: Future[Option[String]] = repo.retrieve(random)(ec).map(_.map(_.url))
      retrieve
    }
  }

}
