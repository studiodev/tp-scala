package tp

import org.scalatest._

class IntListSpec extends UnitSpec {

  "IntList constructor" - {
    "create a linked list from many Ints" in {
      IntList(1, 2) should === (new Cons(1, new Cons(2, IntList.nil)))
    }
  }

  "IntList operations allow to" - {

    "Debug the content of IntList with toString method" in {
      IntList().toString should === ("nil")
      IntList(1).toString should === ("1 :: nil")
      IntList(1, 2).toString should === ("1 :: 2 :: nil")
    }

    "Count the number of values" in {
      IntList().count should === (0)
      IntList(0, 0, 0, 0).count should === (4)
    }

    "Execute a function for each value" in {
      var compteur = 0
      IntList(1, 2, 3).foreach( x => compteur = compteur + x)
      compteur should === (6)
    }

    "Map(transform) the values" in {
      IntList(1, 2, 3).map(x => x + 1) should === (IntList(2, 3, 4))
    }

    "Filter the values" in {
      IntList(1, 2, 3).filter(x => x % 2 == 1) should === (IntList(1, 3))
      IntList(1).filter(x => x > 2) should === (IntList())
    }

    "Sum the values" in {
      IntList().sum should === (0)
      IntList(3).sum should === (3)
      IntList(1, 2, 3).sum should === (6)
    }

    "Product the values" in {
      IntList().sum should === (1)
      IntList(5).sum should === (5)
      IntList(2, 3, 4).sum should === (24)
    }

    "Fold the values" in {
      IntList().fold(0, _+_) should === (0)
      IntList(1, 2).fold(0, _+_) should === (3)

      IntList(1, 2, 3).fold(1, (product, e) => product * e) should === (6)
    }

    "Check if all values verify a predicate" in {
      IntList().forall(x => x % 2 == 0) should === (true)
      IntList(2, 4, 6).forall(x => x % 2 == 0) should === (true)
      IntList(1, 2, 4).forall(x => x % 2 == 0) should === (false)
    }

    "Check if all values verify a boolean predicate" in {
      IntList().foldBool(true, (int, bool) => bool) should === (true)
      IntList().foldBool(false, (int, bool) => bool) should === (false)
      IntList(0, 2, 4).foldBool(false, (int, bool) => bool && int % 2 == 0) should === (false)
    }

    "Check if an element exists (.exists)" in {
      // TODO
      fail
    }

    "Take the N first elements (.take)" in {
      // TODO
      fail
    }

    "Drop the N first elements (.drop)" in {
      // TODO
      fail
    }

    "Take the last element, or -1 if there is no element (.last)" in {
      // TODO
      fail
    }

    "Get the minimum element (.min)" in {
      // TODO
      fail
    }

  }

}