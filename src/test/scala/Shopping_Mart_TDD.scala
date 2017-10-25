import java.util.NoSuchElementException

import org.scalatest.{BeforeAndAfter, FlatSpec, FunSuite, Matchers}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import collection.mutable.Stack

@RunWith(classOf[JUnitRunner])
class Shopping_Mart_TDD extends FunSuite with BeforeAndAfter  {

  // Test case 1 (a): A basic test to see if the scanner works, at this stage the machine should just return a string "Item scanned".
  // Note we didn't give it the ability to distinguish between apple or oranges yet.

  var shop: Shopping_Mart = _

  before{
    shop = new Shopping_Mart
  }

  test("A Scanner should be capable of scanning objects  ") {
    assert(shop.scanItem("Item")=="Item scanned !")
  }

  // Test case 1 (b): Case an empty string provided throw runtimeException
  test("Scanner should throw a RunTimeException when nothing scanned "){
    assertThrows[RuntimeException]{
      shop.scanItem("")
    }

  }





/*
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop()===2)
    assert(stack.pop()===1)
  }
  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    assertThrows[NoSuchElementException]{
      emptyStack.pop()
    }

  }*/

}
