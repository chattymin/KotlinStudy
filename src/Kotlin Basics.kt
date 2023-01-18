/*
Practice Time: Basic Operations
Solve the following using the operator methods in one line of code.

If you start with 2 fish, and they breed twice, producing 71 offspring the first time, and 233 offspring the second time, and then 13 fish are swallowed by a hungry moray eel, how many fish do you have left? How many aquariums do you need if you can put 30 fish per aquarium?

Hint: You can chain method calls.
Hint: You can call the methods on numbers, and Kotlin will convert them to objects for you.
Bonus question: What is special about all the numbers of fish?

fun main(){
    var result = 2.plus(71).plus(233).minus(13)
    println(result)
    println(result/30 + 1)
}
*/

/*
Practice Time: Variables
Create a String variable rainbowColor, set its color value, then change it.
Create a variable blackColor whose value cannot be changed once assigned. Try changing it anyway.

fun main(){
    var rainbowColor: String = "Red"
    rainbowColor = "blue"

    val blackColor: String = "Black"
    blackColor = "White!" // Error => Can't not set "val"
}
*/

/*
Practice Time: Nullability
Try to set rainbowColor to null. Declare two variables, greenColor and blueColor. Use two different ways of setting them to null.

fun main(){
    var rainbowColor = "red"
    rainbowColor = null // Error => Can't set null with out ?

    var greenColor = null
    var blueColor: String? = null

}
 */

/*
Practice Time: Nullability/Lists
Create a list with two elements that are null; do it in two different ways.
Next, create a list where the list is null.

fun main(){
    var arr1 = listOf(null, null)
    var arr2: List<String?> = listOf(null, null)

    var arr3: List<String?>? = null
}
 */

/*
Practice Time: Null Checks
Create a nullable integer variable called nullTest, and set it to null. Use a null-check that increases the value by one if it's not null, otherwise returns 0, and prints the result.
Hint: Use the Elvis operator.

fun main(){
    var nullTest: Int? = 3
    println(nullTest?.inc()?:0)
}
 */

/*
Practice Time
Create three String variables for trout, haddock, and snapper.
Use a String template to print whether you do or don't like to eat these kinds of fish.

fun main(){
    var trout: String = "trout"
    var haddock: String = "haddock"
    var snappe: String = "snappe"
    var now = trout

    when(now){
        trout-> println("I like $trout")
        haddock-> println("I love $haddock")
        snappe-> println("I hate $snappe")
    }
}
 */

/*
Practice Time
when statements in Kotlin are like case or switch statements in other languages.

Create a when statement with three comparisons:

If the length of the fishName is 0, print an error message.
If the length is in the range of 3...12, print "Good fish name".
If it's anything else, print "OK fish name".

fun main() {
    var fishName: String = "snake"

    when(fishName.length){
        0 -> println("Error")
        in 3..12 -> println("Good Fish name")
        else -> println("OK fish name")
    }
}
 */

/*
Practice Time
Looping over arrays and lists is a fundamental technique that has a lot of flexibility in Kotlin. Let's practice.

Basic example
Create an integer array of numbers called numbers, from 11 to 15.
Create an empty mutable list for Strings.
Write a for loop that loops over the array and adds the string representation of each number to the list.
Challenge example
How can you use a for loop to create (a list of) the numbers between 0 and 100 that are divisible by 7?

fun main() {
    var numbers: Array<Int> = Array(5){it + 11}

    var mutableList = mutableListOf<String>()
    for (number in numbers) mutableList.add(number.toString())

    var sevenList = mutableListOf<String>()
    for (number in 7..100 step 7) {
        sevenList.add(number.toString())
    }

    println("First array: ${numbers.asList()}")
    println("Second array: $mutableList")
    println("Third array: $sevenList")
}
 */