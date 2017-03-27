package readmelater

import com.amazonaws.services.dynamodbv2.xspec.B

import scala.language.higherKinds

/**
  * Generic Monad
  */
trait Monad[T[_]] {
  def flatMap[A, B](f: A => T[B]): T[B]
  def map[A, B](f: A => B): T[B]
}
