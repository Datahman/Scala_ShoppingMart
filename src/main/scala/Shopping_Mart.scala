import scala.collection.mutable.ListBuffer

class Shopping_Mart {

  val orangePrice: BigDecimal = BigDecimal(0.60)
  val applePrice: BigDecimal = BigDecimal(0.25)

  var subTotalOrange: BigDecimal = BigDecimal(0);
  var subTotalApple: BigDecimal = BigDecimal(0);
  var Total: BigDecimal = BigDecimal(0);

  var total_items: Int =0; // Hold total no. of items.
  var itemfrequencyMapping: Map[String,Int] = Map()

  var isDiscountON: Boolean=false;


  def readItems(items: String) = {

    if((items=="Orange") || (items=="Apple"))
    {
      items
    }
    else if (items.contains(",")) {
      total_items = items.split(",").map(each_item => (each_item, 1)).length

      itemfrequencyMapping = items.split(",").map(each_item => (each_item, 1)).groupBy(_._1).map { case (item, value) => (item, value.map(_._2).toList.sum) }
    }
    else {
      throw new RuntimeException
    }
  }


  def totalPrice(): BigDecimal = {
    for (each_map <- itemfrequencyMapping) {
      if (each_map._2 >= 1 && each_map._1 == "Orange" && !(isDiscountON==true)) {
        if(each_map._2 >=1){
          subTotalOrange = each_map._2 * orangePrice
          Total += subTotalOrange
        }
      }
      if(each_map._2%3==0  && isDiscountON==true && each_map._1=="Orange"){
        subTotalOrange = 2* orangePrice * (each_map._2/3)
        Total += subTotalOrange
      }
      if(each_map._2%3==2 && isDiscountON==true && each_map._1=="Orange"){
        subTotalOrange = orangePrice * (each_map._2/3 +2)
        Total += subTotalOrange
      }

      if (each_map._2 >= 1 && each_map._1 == "Apple") {
        if(each_map._2%2==0){
          subTotalApple = applePrice * (each_map._2/2)
          Total += subTotalApple
        }
        else if (each_map._2%2 !=0){
          subTotalApple = applePrice * (each_map._2/2 + 1)
          Total += subTotalApple
        }
      }
      else {
        BigDecimal(0)
      } // Terminate loop
    }
    Total
  }

  def setDiscountOnOrange(): Boolean={
   isDiscountON= true
  isDiscountON}
}
