/* Final class:
* It makes use of four main components:
* (i) Scanned object mappings from the SHopping_Mart
* (ii) Call of implicit function as defined on ImplicitMethod
* (iii) Usw of the Discount Switch
* (iv)
*/
class ProcessSaleService (shop:Shopping_Mart)  extends Shopping_Mart with ImplicitMethods with DiscountService {

  var martobjects: Array[(Item, Int)] = Array()
  var apple_unit_price: Double = 0
  var orange_unit_price: Double = 0
  var otherItem_unit_price: Double = 0

  override def getApplePrice: Double = {
    apple_unit_price = shop.getApplePrice()
    apple_unit_price}

  override def getOrangePrice: Double = {
    orange_unit_price = shop.getOrangePrice()
    orange_unit_price}

  override def getOtherItemPrice: Double = {
    otherItem_unit_price = shop.getOtherItemPrice()
    otherItem_unit_price}

  def martObjects(): Array[(Item, Int)] = {
    martobjects = shop.getItemMapping()
    martobjects}


  def callImplicits[T, P, E, K, R](n: T, s: P, c: E, k: K)(implicit sv: getTotalDefinition[T, P, E, K, R]): R = sv.functionToBeOverriden(n: T, s: P, c: E, k: K)

  import ImplicitSpace._
  var appletotal: Double = 0
  var orangetotal:Double=0
  var othertotal:Double=0
  def Total(): Unit = {
    martobjects.foreach{
      case (item, count) => if(item.getClass.getSimpleName=="Apple") {
        appletotal = callImplicits(item, count, apple_unit_price, discountKey)
      }
        if(item.isInstanceOf[Orange]){
          orangetotal=callImplicits(item,count,orange_unit_price,discountKey)
        }
        if(item.isInstanceOf[otherItems]){
          orangetotal=callImplicits(item,count,otherItem_unit_price,discountKey)
        }
    }
  }
}






