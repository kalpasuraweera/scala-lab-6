@main def main(): Unit = {
    var inventory1 = Map(100 -> ("Shirt", 10 , 500), 101 -> ("Trousers", 20, 3000), 102 -> ("Jeans", 30, 1500))
    var inventory2 = Map(103 -> ("Skirt", 40, 2000), 101 -> ("Trousers", 10, 2500), 102 -> ("Jeans", 60, 3000))

    var productNames = inventory1.values.map(_._1).toList
    println(s"Product Names of Inventory 1: $productNames")
    var totalValue = inventory1.values.map(x => x._2 * x._3).sum
    println(s"Total Value of Inventory 1: $totalValue")

    if(inventory1.isEmpty)
        println("Inventory 1 is empty")
    else
        println("Inventory 1 is not empty")

    inventory1 = (inventory1 ++ inventory2).map(x => {
        if(inventory1.contains(x._1) && inventory2.contains(x._1))
            x._1 -> (x._2._1, x._2._2 + inventory1(x._1)._2, inventory1(x._1)._3 > x._2._3 match {
                case true => inventory1(x._1)._3
                case false => x._2._3
            })
        else
            x
    })

    println(s"Combined Inventory: $inventory1")

    var productID: Int = 102

    if(inventory1.contains(productID))
        println(s"Product with ID ${productID} is present in the inventory")
        println(s"Details of Product with ID ${productID}: ${inventory1(productID)}")
    else
        println(s"Product with ID ${productID} is not present in the inventory")
    
}