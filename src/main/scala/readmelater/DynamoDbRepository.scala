package readmelater

import awscala.dynamodbv2.DynamoDB
import readmelater.DynamoDbRepository.CtxFuture

import scala.concurrent.{ExecutionContext, Future}

/**
  * Allows persisting ReadItems into DynamoDB
  */
class DynamoDbRepository extends IO[CtxFuture] {

//  implicit val db = DynamoDB.local()
  var db = Map.empty[Int, ReadItem]

  def insert(readItem: ReadItem): CtxFuture[Boolean] = implicit ec => Future {
    db = db + (db.size -> readItem)
    true
  }

  def retrieve(index: Int): CtxFuture[Option[ReadItem]] = implicit ec => Future {
    db.get(index)
  }

  def remove(index: Int): CtxFuture[Boolean] = implicit ec => Future {
    if(db.contains(index)) {
      db = db - index
      true
    } else {
      false
    }
  }

  def count: CtxFuture[Int] = implicit ec => Future(db.size)

}

object DynamoDbRepository {
  type CtxFuture[T] = ExecutionContext => Future[T]

//  implicit object FutureMonad extends Monad[Future] {
//    override def flatMap[A, B](m: Future[A], f: (A) => Future[B]): Future[B] = m.flatMap(f)
//    override def map[A, B](m: Future[A], f: (A) => B): Future[B] = m.map(f)
//  }
}