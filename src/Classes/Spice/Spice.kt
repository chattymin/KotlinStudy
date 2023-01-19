package Classes.Spice

/*
Practice Time
Let's improve our SimpleSpice class so that we can have various spices with different levels of spiciness.

Create a new class, Spice.
Pass in a mandatory String argument for the name, and a String argument for the level of spiciness where the default value is mild for not spicy.
Add a variable, heat, to your class, with a getter that returns a numeric value for each type of spiciness.
Instead of the list of spices as Strings you used earlier, create a list of Spice objects and give each object a name and a spiciness level.
Add an init block that prints out the values for the object after it has been created. Create a spice.
Create a list of spices that are spicy or less than spicy. Hint: Use a filter and the heat property.
Because salt is a very common spice, create a helper function called makeSalt().
 */

class Spice(var name: String, var spciness: String = "mild") {
    var heat = when(spciness){
        "non" -> 1
        "mild" -> 3
        "hot" -> 5
        "spicy" -> 7
        "bomb" -> 10
        else -> 0
    }
    init {
        println("name = $name, spiciness = $spciness, heat = $heat")
    }
}

var spice = listOf<Spice>(
    Spice("salt", "non"),
    Spice("curry", "mild"),
    Spice("pepper", "hot"),
    Spice("ShinRamyeon", "spicy"),
    Spice("BuldakRamyeon", "bomb")
)

var spiceList = spice.filter { it.heat <= 5 }

fun makeSalt() = Spice("salt")
