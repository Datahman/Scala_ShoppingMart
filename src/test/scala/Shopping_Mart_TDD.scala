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
  //var ProcessSaleService = _
  var shop: Shopping_Mart = _
  var sales: ProcessSaleService = _


  var inputString: String = ""
  before {
    shop = new Shopping_Mart
    sales = new ProcessSaleService(shop)


  }


  // Throw runtime exception when empty string provided
  test("WhenEmptyStringGivenReturnRuntimeException") {
    assertThrows[RuntimeException] {
      shop.readItems("")
    }

  }


  // Basic test all items scanned
  test("WhenSingleItemScannedCheckInput") {
    assert(shop.readItems("Apple") == "Apple")
  }


  // Make the scanner differentiate from multiple items, based on ",", so two items must be present
  test("GivenStringsAreCommaSeparatedCheckInput") {
    //assert(shop.readItems("")==0)
    shop.readItems("Apple,Orange,Orange")
    assert(shop.total_items == 3)
  }

  // Check item frequency mapping
  test("GivenMultipleItemsScannedShowReducedKeyValueMap") {
    shop.readItems("Apple,Apple,Apple,Apple,Orange,Orange")
    val Mapping: Int= shop.itemfrequencyMapping.toList.length
    //print(Mapping)
    //Mapping should not contain (Some(1)) // Check for absence of 1 from container Map ((Apple,4),(Orange,2))
    //Mapping should contain atMostOneOf("Orange", "Apple")
  }

  // Check default price values
  test("CheckDefaultPriceValuesShouldBeZero") {
    assert(shop.applePrice == 0.0)
    assert(shop.orangePrice == 0.0)
    assert(shop.otherItemPrice == 0.0)
    /*
    shop.setApplePrice("Apple",1.5)
    val applePrice = shop.applePrice
    assert(applePrice==1.5)
*/
  }

  // Check price setters
  test("GivenInputPricesCheckSetterMethods") {
    shop.setApplePrice("Apple", 1.5)
    assert(shop.applePrice == 1.5)

    shop.setOrangePrice("Orange", 2.5)
    assert(shop.orangePrice == 2.5)

    shop.setItemPrice("Item", 3.5)
    assert(shop.otherItemPrice == 3.5)
  }

  // Initially zero
  /*test("SaleInstanceInitialTest"){
    val martobject_length_atSales = sales.martobjects.length
   // print(martobject_length_atSales==0)
  }*/

  // Check integration between Shopping_Mart and SaleService
  test("a"){
    shop.readItems("Apple,Apple,Apple,Apple,Orange,Orange")
    var saleInstanceItemMappingNotEmpty = sales.martObjects()
   // print(saleInstanceItemMappingNotEmpty)
    //assert(saleInstanceItemMappingNotEmpty==6)
  }

  // Check item prices received by sales instance
  test("Sales instance price validation") {
    shop.setApplePrice("Apple", 1.5)
    assert(shop.applePrice == 1.5)

    shop.setOrangePrice("Orange", 2.5)
    assert(shop.orangePrice == 2.5)

    shop.setItemPrice("Item", 3.5)
    assert(shop.otherItemPrice == 3.5)

//    assert(sales.apple_price_f==1.5)
//    assert(sales.orange_Price_f==2.5)
//    assert(sales.otherItemPrice_f==3.5)
  }
  // Check getTotal results
  test("CheckgetTotal"){
    shop.readItems("Apple,Apple,Apple,Apple,Orange,Orange,Orange")
   // shop.itemfrequencyMapping.foreach(println)
    //shop.getScanMappings.foreach(println)
    shop.setApplePrice("Apple", 1.5)
    shop.setOrangePrice("Orange", 2.5)
    shop.setItemPrice("Item",2.3)

    sales.getApplePrice()
    sales.getOrangePrice()
    //sales.getOtherItemPrice()


    val scannedInstancesonSaleService = sales.martObjects()
    sales.Total()
    print(sales.appletotal,sales.orangetotal,sales.othertotal)
  }

}

/*

  // Test specification: Update price for consecutive apples bought
  // Conditions to check (i) OddCountOfApple (ii) EvenCountOfApple

  test("GivenBOGOFOnEvenAndOddCountThenReturnNewSumForApple"){
    shop.readItems("Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple,Apple")
    shop.setDiscountOnOrange()

    shop.totalPrice()
    val evenCountAppleTotal: BigDecimal = shop.subTotalApple
    assert(evenCountAppleTotal==3.0)
    shop.readItems("Apple,Apple,Apple")
    shop.totalPrice()
    val oddCountAppleTotal: BigDecimal = shop.subTotalApple
    assert(oddCountAppleTotal==1.2)
  }


  // Test specification: Update price for three bananas bought at cost of two.
  // Conditions to check (i) Price on OddCountOfBanana (ii) Price on EvenCountOfBanana

  test("GivenBOGOFOnEvenAndOddCountThenReturnNewSumForOrange") {
    shop.readItems("Orange,Orange,Orange,Orange,Orange,Orange,Orange,Orange,Orange")

    val Mapping: Map[String, Int] = shop.itemfrequencyMapping
    shop.setDiscountOnOrange()
    shop.totalPrice()
    val evenOrangeTotal: BigDecimal = shop.subTotalOrange
    assert(evenOrangeTotal == BigDecimal(6 * 0.25).setScale(2,BigDecimal.RoundingMode.HALF_UP))

  }
  test("asa"){
    shop.readItems("Apple,Apple,Orange,Apple")
    shop.totalPrice()
    val tot1: BigDecimal = shop.subTotalApple
    val tot2: BigDecimal = shop.subTotalOrange
    assert(tot1+tot2 == BigDecimal(1.8+0.25))


  }





/*
  var a = readLine("Enter string")
  var fruitArray: String = a

  val each_word = fruitArray.split(",").map(word=>(word,1))

  val list_OfWords = each_word.toList
  val word_frequency_pair = each_word.groupBy(_._1).map{case (item, value) => (item,value.map(_._2).toList.sum)}
*/
*/










/* Realisations/iSSUES: i) Set price is embedded onto the last class "ProcessScanService" which breaks SHoppingMart instantiation => Unit()
(ii) Set price method for each i.e appale,orange item on the base class Shopping mart.
(iii) During inheriting attributes, the child class must have methods to get them
(iv) Mistake found on implicit implementation since items passed are being passed on to the getOtherItem function even though NO DIRECT call is being made, since the condition: @
if (n.isInstanceOf[Item] && k == false) {
          otheritemTotal += s * c //
IS FULFILLED FOR CASES WHEN ITEM PASSED, != APPLE OR ORANGE ARE STILL INSTSNCES OF ITEM!
Correct version:

if (n.isInstanceOf[Item] && k == false) {
          otheritemTotal = s * c //







*/

