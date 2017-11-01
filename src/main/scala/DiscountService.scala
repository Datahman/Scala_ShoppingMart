// A discount On/Off switch
trait DiscountService {
  var discountKey: Boolean = false

  def discountSwitch(): Boolean = {
    if(discountKey==false){

      discountKey=true
    }
    else if (discountKey==true){
      discountKey=false
    }
    discountKey}

}







