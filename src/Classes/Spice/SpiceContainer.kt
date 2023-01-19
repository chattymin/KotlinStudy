package Classes.Spice
/*
Practice Time
Create a simple data class, SpiceContainer, that holds one spice.
Give SpiceContainer a property, label, that is derived from the name of the spice.
Create some containers with spices and print out their labels.
 */
// I used Spice Class that I wrote before

class SpiceContainer (val spice: Spice){
    val label = spice.name
}

val SpiceList = listOf<SpiceContainer>(
    SpiceContainer(Spice("YellowCurry","mild")),
    SpiceContainer(Spice("RedCurry", "hot")),
    SpiceContainer(Spice("BlackCurry", "spicy"))
)

fun main() {
    for (curry in SpiceList) println(curry.label)
}