class Shopping_Mart {
  var item:String=""


  def scanItem(item:String)={
    if(item!=""){
    item +" "+"scanned !"
    }
    else if(item==""){
      throw new RuntimeException
    }
  }

}
