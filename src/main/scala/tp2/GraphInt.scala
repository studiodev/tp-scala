package tp2

trait Graph {
  def countLeafs: Int
  def countNodes: Int
  def sum: Int
  def contains(i: Int): Boolean
  def foreach(f: Int => Unit): Unit
  def map(f: Int => Int): Graph
  def forAll(p: Int => Boolean): Boolean
  def cutAtLevel(level: Int): Graph
}

case class Node(value: Int, left: Graph, right: Graph) extends Graph {
  def countLeafs = ???
  def countNodes = ???
  def sum = ???
  def contains(i: Int) = ???
  def forAll(p: Int => Boolean) = ???
  def foreach(f: Int => Unit) = ???
  def map(f: Int => Int) = ???
  def cutAtLevel(level: Int) = ???

  override def toString = s"$value[$left,$right]"
}

case object Leaf extends Graph {
  def countLeafs = ???
  def countNodes = ???
  def sum = ???
  def contains(i: Int) = ???
  def forAll(p: Int => Boolean) = ???
  def foreach(f: Int => Unit) = ???
  def map(f: Int => Int) = ???
  def cutAtLevel(level: Int) = ???

  override def toString = "*"
}
