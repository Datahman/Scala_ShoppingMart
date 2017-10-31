

class Shopping_Mart extends MakeObjectService with setItemPrice {
  var orangePrice: Double =0.0
  var applePrice: Double = 0.0
  var otherItemPrice: Double =0.0
  // Set prices
  def setApplePrice(item: String, item_price: Double): Double = {
    if (item_price.isInstanceOf[Double] && item == "Apple") {
      applePrice = item_price
    } else {
      throw new RuntimeException("Prices must be of type Double only !")}
    applePrice}

  def setOrangePrice(item: String, item_price: Double): Double = {
    if (item_price.isInstanceOf[Double] && item == "Orange") {
      orangePrice = item_price
    } else {
      throw new RuntimeException("Prices must be of type Double only !")}
    orangePrice}

  def setItemPrice(item: String, item_price: Double): Double = {
    if (item_price.isInstanceOf[Double] && (item != "Apple" && item != "Orange")) {
      otherItemPrice = item_price}
    else {throw new RuntimeException("Prices must be of type Double only !")}
    otherItemPrice}
  def getApplePrice():Double={applePrice}
  def getOrangePrice():Double={orangePrice}
  def getOtherItemPrice():Double={otherItemPrice}

  var total_items: Int =0; // Hold total no. of items.
  var itemfrequencyMapping: Array[(Item,Int)] = Array()

  def readItems(items: String) = {

    if((items=="Orange") || (items=="Apple"))
    {
      items
    } else if (items.contains(","))
    { total_items = items.split(",").map(each_item => (each_item, 1)).length
      itemfrequencyMapping = items.split(",").map(each_item => (each_item, 1))
        .groupBy(_._1).map{case (item,value) => (item, value.map(_._2).toList.sum)}.map{
        case (a,b) if (a=="Apple") => (makeAppleObject(a),b)
        case (a,b) if (a=="Orange") => (makeOrangeObject(a),b)
        case (a,b) if ((a!="Apple")||(a!="Orange")) => (makeOtherItemObject(a),b)
      }.toArray
    }
    else {
      throw new RuntimeException
    }
  }
  def getItemMapping():Array[(Item,Int)]={itemfrequencyMapping}
}

trait setItemPrice  {
  def setApplePrice(item:String,item_price:Double):Double
  def setOrangePrice(item:String,item_price:Double):Double
  def setItemPrice(item:String,item_price:Double):Double
}

