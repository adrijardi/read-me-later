package readmelater

import scala.language.higherKinds

/**
  * Higher kind for a generic IO element
  */
trait IO[P[_]] {

  def insert(readItem: ReadItem): P[Boolean]

  def retrieve(index: Int): P[Option[ReadItem]]

  def remove(index: Int): P[Boolean]

  def count: P[Int]

}
