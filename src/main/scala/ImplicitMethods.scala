//Implementation of implicit/generic functions, taking in Apple,Orange or OtherItem objects

trait ImplicitMethods extends countFunctions{
  abstract class getTotalDefinition[T, P, E, K, R] {
    def functionToBeOverriden(n: T, s: P, c: E, k: K): R}


  object ImplicitSpace extends countFunctions {


    // Implementations of the function definition within the abstract class using helper functions defined below

    implicit object getAppleTotal extends getTotalDefinition[Apple, Int, Double, Boolean, Double] {
      var appleTotal: Double = 0

      def functionToBeOverriden(n: Apple, s: Int, c: Double, k: Boolean): Double = {

        if (n.isInstanceOf[Apple] && k == false) {
          appleTotal = s * c
        }
        if ((n.isInstanceOf[Apple]) && (s % 2 == 0) && (k == true)) {
          appleTotal = c * evenAppleCount(s)
        }
        if ((n.isInstanceOf[Apple]) && (s % 2 == 1) && (k == true)) {
          appleTotal = c * (oddAppleCount(s) + 1)
        }
        else if (!n.isInstanceOf[Apple]) {
          appleTotal += 0
        }
        appleTotal
      }

      appleTotal
    }


    implicit object getOrangeTotal extends getTotalDefinition[Orange, Int, Double, Boolean, Double] {
      var orangeTotal: Double = 0

      override def functionToBeOverriden(n: Orange, s: Int, c: Double, k: Boolean): Double = {
        if (n.isInstanceOf[Orange] && k == false) {
          orangeTotal = s * c
        }
        if ((n.isInstanceOf[Orange]) && (s % 3 == 0) && (k == true)) {
          orangeTotal = 2 * c * evenOrangeCount(s)
        }
        if (n.isInstanceOf[Orange] && (s % 3 == 2) && (k == true)) {
          orangeTotal = c * oddOrangeCount(s)
        }
        else if (!n.isInstanceOf[Orange]) {
          orangeTotal += 0
        }
        orangeTotal
      }
      orangeTotal
    }


    implicit object getOtherItemTotal extends getTotalDefinition[Item, Int, Double, Boolean, Double] {
      var otheritemTotal: Double = 0

      override def functionToBeOverriden(n: Item, s: Int, c: Double, k: Boolean): Double = {
        if (n.isInstanceOf[Item] && k == false) {
          otheritemTotal = s * c
        }
        otheritemTotal
      }
    }
  }


}

  trait countFunctions {
    // Count methods
    def evenOrangeCount(count: Int): Double = {(count / 3).toDouble}
    def oddOrangeCount(count: Int): Double = {(count / 3 + 2).toDouble}
    def evenAppleCount(count: Int): Double = {(count * 1 / 2).toDouble}
    def oddAppleCount(count: Int): Double = {count.toDouble * 1 / 2.toDouble}
  }



/* n: T,s:P,c:E,k:K
 * n: Int => Will represent an_item : Item e.g apple: Apple, orange: Orange etc
 * s: Int => Count of item
 * c: Int => Item_price
 * k: Boolean false or true
 */
//val orangePrice: Double = 0.25
//val applePrice: Double= 0.60


//object Demo extends App with test{ // inheritance fails since trait Demo is not a child class of Shopping_mart
//dump.foreach(println)


