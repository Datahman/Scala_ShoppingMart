import java.math.MathContext
import java.util.NoSuchElementException

import scala.io.StdIn.readLine
import org.scalatest.{BeforeAndAfter, FlatSpec, FunSuite, Matchers}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import collection.mutable.Stack
import scala.math.BigDecimal.RoundingMode

@RunWith(classOf[JUnitRunner])
class Shopping_Mart_TDD extends FunSuite with BeforeAndAfter with Matchers {
  var shop: Shopping_Mart = _


  var inputString: String = ""
  before {
    shop = new Shopping_Mart

  }

  // Throw runtime exception when empty string provided
  test("WhenEmptyStringGivenReturnRuntimeException"){
    assertThrows[RuntimeException]{shop.readItems("")}

  }

  // Basic test all items scanned
  test("WhenSingleItemScannedCheckInput"){
    assert(shop.readItems("Apple")=="Apple")
  }

  // Make the scanner differentiate from multiple items, based on ",", so two items must be present
  test("GivenStringsAreCommaSeparatedCheckInput"){
    //assert(shop.readItems("")==0)
    shop.readItems("Apple,Orange,Orange")
    assert(shop.total_items==3)
  }

  // Check item frequency mapping
  test("GivenMultipleItemsScannedShowReducedKeyValueMap"){
    shop.readItems("Apple,Apple,Apple,Apple,Orange,Orange")
    val Mapping: Map[String,Int]  = shop.itemfrequencyMapping
    Mapping  get "Apple" should not be  (Some(1)) // Check for absence of 1 from container Map ((Apple,4),(Orange,2))
    Mapping should contain key "Orange" // Check key 'Orange' present

    // Command: // print(Mapping.toString())
    // Result: // Map(Orange -> 2, Apple -> 4)
  }

  test("GivenTheCountMappingsFIndtotalCostOfFruits"){
    shop.readItems("Apple,Apple,Apple,Apple,Orange,Orange")
    val Mapping: Map[String,Int]  = shop.itemfrequencyMapping
    shop.totalPrice()
    val orangeTotal: BigDecimal =  shop.subTotalOrange

    assert(orangeTotal== 1.20)
    val appleTotal: BigDecimal = shop.subTotalApple
    assert(appleTotal==0.5)
    val TotalCost: BigDecimal = shop.Total
    assert(TotalCost == (orangeTotal+appleTotal))
  }

  // Test specification: Update price for consecutive apples bought
  // Conditions to check (i) OddCountOfApple (ii) EvenCountOfApple

  test("GivenBOGOFOnEvenAndOddCountThenReturnNewSumForApple"){
    shop.readItems("Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple")

    val Mapping: Map[String,Int]  = shop.itemfrequencyMapping
    shop.totalPrice()
    val evenCountAppleTotal: BigDecimal = shop.subTotalApple
    assert(evenCountAppleTotal==1.25)

    shop.readItems("Apple,Apple,Apple") //print(shop.total_items) # 3
    shop.totalPrice()
    val oddCountAppleTotal: BigDecimal = shop.subTotalApple
    assert(oddCountAppleTotal==0.5)
  }

  // Test specification: Update price for three bananas bought at cost of two.
  // Conditions to check (i) Price on OddCountOfBanana (ii) Price on EvenCountOfBanana

  test("GivenBOGOFOnEvenAndOddCountThenReturnNewSumForOrange") {
    shop.readItems("Orange,Orange,Orange,Orange,Orange,Orange,Orange,Orange,Orange")

    val Mapping: Map[String, Int] = shop.itemfrequencyMapping
    shop.setDiscountOnOrange()
    shop.totalPrice()
    val evenOrangeTotal: BigDecimal = shop.subTotalOrange
    assert(evenOrangeTotal == BigDecimal(6 * 0.6).setScale(2,BigDecimal.RoundingMode.HALF_UP))

/*
    shop.readItems("Orange,Orance,Orange,Orange")
    shop.totalPrice()
    val EvenOrangeCountAndOneLoneOrangeTotal: BigDecimal = shop.subTotalOrange
    assert(EvenOrangeCountAndOneLoneOrangeTotal == BigDecimal((5%3 +2)*0.6).setScale(2,BigDecimal.RoundingMode.HALF_UP))
  */
  }





/*
  var a = readLine("Enter string")
  var fruitArray: String = a

  val each_word = fruitArray.split(",").map(word=>(word,1))

  val list_OfWords = each_word.toList
  val word_frequency_pair = each_word.groupBy(_._1).map{case (item, value) => (item,value.map(_._2).toList.sum)}
*/
}








