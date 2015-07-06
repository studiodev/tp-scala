package tp

/**
 * IntList public interface
 */
sealed abstract class IntList {

  def foreach(f: Int => Unit): Unit

  def count: Int

  def map(f: Int => Int): IntList

  def filter(p: Int => Boolean): IntList

  def sum: Int

  def product: Int

  def fold(z: Int, op: (Int, Int) => Int): Int

  def forall(p: Int => Boolean): Boolean

  def foldBool(z: Boolean, op: (Int, Boolean) => Boolean): Boolean

  def exists(p: Int => Boolean): Boolean

  def take(n: Int): IntList

  def drop(n: Int): IntList

  def last: Option[Int]

  def min: Option[Int]

}

/**
 * Convenient factories
 */
object IntList {

  /** @return an empty list */
  def nil: IntList = tp.Nil

  /** @return a list with a `head` element and a `tail` list */
  def cons(head: Int, tail: IntList): IntList = Cons(head, tail)

  /** @example IntList(1, 2, 3) */
  def apply(xs: Int*): IntList = xs.foldRight(nil)(cons)

}

/**
 * Empty list
 */
case object Nil extends IntList {

  override def toString = "nil"

  def foreach(f: Int => Unit) {
    ???
  }

  def count = ???

  def map(f: Int => Int): IntList = ???

  def filter(p: (Int) => Boolean) = ???

  def sum = ???

  def product = ???

  def fold(z: Int, op: (Int, Int) => Int): Int = ???

  def forall(p: Int => Boolean): Boolean = ???

  def foldBool(z: Boolean, op: (Int, Boolean) => Boolean): Boolean = ???

  def exists(p: Int => Boolean): Boolean = ???

  def take(n: Int): IntList = ???

  def drop(n: Int): IntList = ???

  def last: Option[Int] = ???

  def min: Option[Int] = ???

}

/**
 * List with a `head` element and a `tail` list
 * @param head Head element
 * @param tail Tail list
 */
case class Cons(head: Int, tail: IntList) extends IntList {

  override def toString = s"cons($head, $tail)"

  def foreach(f: Int => Unit) {
    ???
  }

  def count = 1 + tail.count

  def map(f: Int => Int): IntList = ???

  def filter(p: (Int) => Boolean) = {
    ???
  }

  def sum = ???

  def product = ???

  def fold(z: Int, op: (Int, Int) => Int) = ???

  def forall(p: (Int) => Boolean) = ???

  def foldBool(z: Boolean, op: (Int, Boolean) => Boolean) = ???

  def exists(p: Int => Boolean): Boolean = {
    // version récursive
    // puis version avec foldBool
    ???
  }

  def take(n: Int): IntList = {
    ???
  }

  def drop(n: Int): IntList = {
    ???
  }

  def last: Option[Int] = {
    ???
  }

  def min: Option[Int] = {
    ???
  }

}

