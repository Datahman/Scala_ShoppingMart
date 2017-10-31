

class ProcessSaleService (shop:Shopping_Mart)  extends Shopping_Mart with ImplicitMethods with DiscountService {

  var martobjects: Array[(Item, Int)] = Array()
  var apple_unit_price: Double = 0
  var orange_unit_price: Double = 0
  var otherItem_unit_price: Double = 0

  override def getApplePrice: Double = {
    apple_unit_price = shop.getApplePrice()
    apple_unit_price
  }

  override def getOrangePrice: Double = {
    orange_unit_price = shop.getOrangePrice()
    orange_unit_price
  }

  override def getOtherItemPrice: Double = {
    otherItem_unit_price = shop.getOtherItemPrice()
    otherItem_unit_price
  }

  def martObjects(): Array[(Item, Int)] = {
    martobjects = shop.getItemMapping()
    martobjects
  }


  import ImplicitSpace._

  def callImplicits[T, P, E, K, R](n: T, s: P, c: E, k: K)(implicit sv: getTotalDefinition[T, P, E, K, R]): R = sv.functionToBeOverriden(n: T, s: P, c: E, k: K)

  // var total Array_: [Int] = Array()
  // Correcion to make on theb getTotal method: Place the item price setters within implicits

  var appletotal: Double = 0

  def appleTotal() = {
    martobjects.map {
      case (item, count) =>
        if (item.isInstanceOf[Apple]) {
          appletotal += callImplicits(item, count, apple_unit_price, discountKey)
          appletotal
        }
        appletotal
    }.foreach(print)
  }

  var orangetotal: Double = 0

  def orangeTotal() = {
    martobjects.filter(item => !item._1.isInstanceOf[Apple]).map{
      case(item,count) => {
        orangetotal +=callImplicits(item,count,orange_unit_price,discountKey)
        orangetotal
      }
    }.foreach(print)
  }

  var otheritemtotal: Double = 0

  def otherItemTotal() = {
    martobjects.map {
      case (item, count) =>
        if (item.isInstanceOf[Apple]) {
          otheritemtotal += callImplicits(item, count,otherItem_unit_price, discountKey)
        }
        otheritemtotal
    }
  }

}






  object xz extends App {
    val inst = new Shopping_Mart
    inst.setApplePrice("Apple", 0.6)
    inst.setOrangePrice("Orange", 0.3)
    inst.readItems("Apple,Orange")
    val saleinst = new ProcessSaleService(inst)

    saleinst.martObjects()
    val er = saleinst.martObjects()

    for (i <- er) {
      println(i._1.isInstanceOf[Item], i._2)
    }
  }


