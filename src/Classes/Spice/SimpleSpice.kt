package Classes.Spice

/*
Practice Time
Earlier, we created and filtered a list of spices. Spices are much better represented as objects than as simple strings. Because they are objects, we can perform different things with them - such as cooking.

To recap, let's make a simple Spice class. It doesn't do much, but it will serve as the starting point for the next practice.

Create class, SimpleSpice.
Let the class be a property with a String for the name of the spice, and a String for the level of spiciness.
Set the name to curry and the spiciness to mild.
Using a string for spiciness is nice for users, but not useful for calculations. Add a heat property to your class with a getter that returns a numeric value for spiciness. Use a value of 5 for mild.
Create an instance of SimpleSpice and print out its name and heat.
 */

class SimpleSpice {
    var name = "curry"
    var spciness = "mild"

    val het: Int
        get() {return 5}

    var heat = when(spciness){
        "mild" -> 5
        else -> 0
    }
}

fun main() {
    var simpleSpice = SimpleSpice()
    println("name = ${simpleSpice.name}, heat = ${simpleSpice.heat}")
}