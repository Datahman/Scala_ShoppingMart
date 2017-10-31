
class MakeObjectService {
  var appleinstance: Any = 0
  var orangeinstance: Any = 0
  var otherinstance: Any = 0

  def makeAppleObject(item_name: String): Apple = {
    if (item_name == "Apple") {
      appleinstance = Class.forName("Apple").newInstance().asInstanceOf[ {def setName(item_name: String): String}]
    }

    appleinstance.asInstanceOf[Apple]
  }

  def makeOrangeObject(item_name: String): Orange = {
    if (item_name == "Orange") {
      orangeinstance = Class.forName("Orange").newInstance().asInstanceOf[ {def setName(item_name: String): String}]
    }

    orangeinstance.asInstanceOf[Orange]
  }

  def makeOtherItemObject(item_name: String): otherItems = {
    if (item_name != "Orange" && item_name != "Apple") {
      otherinstance = Class.forName("Item").newInstance().asInstanceOf[ {def setName(item_name: String): String}]
    }
    otherinstance.asInstanceOf[otherItems]
  }

}

abstract class Item{def setName(item:String):String}
class Apple extends  Item {override def setName(item_name:String): String = {item_name}}
class Orange extends Item{override def setName(item_name:String):String={item_name}}
class otherItems extends Item{override def setName(item_name:String):String={item_name}}

object ere extends App{
  val r = new MakeObjectService()
  val er = r.makeAppleObject("Apple")
  print(er.isInstanceOf[Apple])
}
