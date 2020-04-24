package ru.solovyev

/*Небольшой пример использования классов, типов, методов, переменных и циклов в Scala*/
object Example {
  type Ingredient = String
  type Dough = Ingredient
  type TomatoSauce = Ingredient
  type Pepperoni = Ingredient
  type Cheese = Ingredient
  type Pizza = Seq[Ingredient]
  type IngredientList = (Dough, Cheese, TomatoSauce, Pepperoni)

  val makePizza: IngredientList => Option[Pizza] = {
    case (dough: Dough, cheese: Cheese, tomatoSauce: TomatoSauce, pepperoni: Pepperoni) => for {
      step1 <- startMaking(dough)
      step2 <- addTomatoSauce(step1, tomatoSauce)
      step3 <- addPepperoni(step2, pepperoni)
      pizza <- addCheese(step3, cheese)
    } yield pizza
  }

  val startMaking = (dough: Dough) => Some(Array(dough))
  val addTomatoSauce = (pizza: Pizza, tomatoSauce: TomatoSauce) => Some(pizza :+ tomatoSauce)
  val addPepperoni = (pizza: Pizza, pepperoni: Pepperoni) => Some(pizza :+ pepperoni)
  val addCheese = (pizza: Pizza, cheese: Cheese) => Some(pizza :+ cheese)

  def main(args: Array[String]): Unit = {
    for {
      drink <- makePizza("Puffy Dough", "Red Tomato Sauce", "Pepperoni", "Mozzarella")
      msg <- drink mkString("Your pizza contains: ", ", ", ".")
    } print (msg)
  }
}
