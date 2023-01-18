import java.lang.Math.random
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cbrt

/*
Basic Task
Create a new Kotlin file.
Copy and paste the main() function from Hello World into the file.
Create a new function, dayOfWeek().
In the body of the function, print "What day is it today?"
Call dayOfWeek() from main().
Run your program.

Extended Task
In the body of the dayOfWeek() function, answer the question by printing the current day of the week.

Hints
You can use Java libraries (java.util) from Kotlin. For example, to get the day of the week:
Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
Type in the code, then press Option + Enter in Mac, or Alt + Enter in Windows, over the red Calendar class to import the library.
Use a when statement to print a string depending on the day. Sunday is the first day of the week.

fun main() {
    dayOfWeek()
}

fun dayOfWeek(){
    println("What day is it today?")

    println(when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "ERROR"
    })
}
 */

/*
Greetings, Kotlin
Exercise: Greetings, Kotlin
Create a main() function that takes an argument representing the time in 24-hour format (values between and including 0 -> 23).

In the main() function, check if the time is before midday (<12), then print "Good morning, Kotlin"; otherwise, print "Good night, Kotlin".

Notes:
Remember that all main() function arguments are Strings, so you will have to convert this argument to an Int before you can apply the check.

Advanced
Try to use Kotlin's string templates to do this in 1 line.

fun main(args: Array<String>) {
    println("${if (args[0].toInt() < 12) "Good morning, Kotlin" else "Good night, Kotlin"}")
}
 */

/*
Practice Time
Create a program with a function that returns a fortune cookie message that you can print.

Create a main() function.
From the main() function, call a function, getFortuneCookie(), that returns a String.
Create a getFortuneCookie() function that takes no arguments and returns a String.
In the body of getFortuneCookie(), create a list of fortunes. Here are some ideas:

"You will have a great day!"
"Things will go well for you today."
"Enjoy a wonderful day of success."
"Be humble and all will turn out well."
"Today is a good day for exercising restraint."
"Take it easy and enjoy life!"
"Treasure your friends because they are your greatest fortune."
Below the list, print: "Enter your birthday: "

Hint: Use print(), not println()
Create a variable, birthday.
Read the user's input form the standard input and assign it to birthday. If there is no valid input, set birthday to 1.
Hint: Use readLine() to read a line of input (completed with Enter) as a String.
Hint: In Kotlin, you can use toIntOrNull() to convert a number as a String to an Integer numeric. If the user enters "", toIntOrNull returns null.
Hint: Check for null using the ? operator and use the ?: operator to handle the null case.
Divide the birthday by the number of fortunes, and use the remainder as the index for the fortune to return.
Return the fortune.
In main(), print: "Your fortune is: ", followed by the fortune string.
Extra practice:
Use a for loop to run the program 10 times, or until the "Take it easy" fortune has been selected.

fun main() {
    repeat(10){
        var result: String = getFortuneCookie()
        println("Your fortune is: $result")
        if (result.contains("Take it easy")) return
    }
}

fun getFortuneCookie(): String{
    val fortune = arrayListOf<String>(
        "You will have a great day!",
        "Things will go well for you today.",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune."
    )
    print("Enter your birthday: ")
    var birthday = readLine()?.toIntOrNull()?:1
    return fortune.get(birthday.rem(fortune.size))
}
 */
/*
Practice Time
Use the code you created in the last practice, or copy the starter code from below.

The getFortune() function should really only be getting the fortune, and not be in the business of getting the birthday.

Change your Fortune Cookie program as follows:

Create a function called getBirthday() that gets the birthday from the user.
Pass the result of getBirthday() to getFortune() using an Integer argument, and use it to return the correct fortune.
Remove getting the birthday from getFortune()
Instead of calculating the fortune based on the birthday, use a when statement to assign some fortunes as follows (or use your own conditions):
If the birthday is 28 or 31...
If the birthday is in the first week…
else … return the calculated fortune as before.
Hint: There are several ways in which to make this when statement. How much can you Kotlinize it?

Starter Code:

fun main(args: Array<String>) {
   var fortune: String
   for (i in 1..10) {
      fortune = getFortune(getBirthday())
      println("\nYour fortune is: $fortune")
      if (fortune.contains("Take it easy")) break;
   }
}

fun main(args: Array<String>) {
    var fortune: String
    for (i in 1..10) {
        fortune = getFortune(getBirthday())
        println("\nYour fortune is: $fortune")
        if (fortune.contains("Take it easy")) break;
    }
}

fun getFortune(day: Int) = when(day) {
    in 1..5 -> "You will have a great day!"
    in 6..10 -> "Things will go well for you today."
    in 11..15 -> "Enjoy a wonderful day of success."
    in 16..19 -> "Be humble and all will turn out well."
    in 20..25 -> "Today is a good day for exercising restraint."
    in 26..30 -> "Take it easy and enjoy life!"
    else -> "Treasure your friends because they are your greatest fortune."
}


fun getBirthday(): Int{
    print("Enter your birthday: ")
    return readLine()?.toIntOrNull()?:1
}
*/

/*
Fit More Fish
Exercise: Fit More Fish
Create a function that checks if we can add another fish into a tank that already has fish in it.

How many fish in a tank?
The most widely known rule for stocking a tank is the one-inch-per-fish-per-gallon-of-water rule. However that's assuming the tank doesn't have any decorations in it.

Typically, a tank with decorations can contain a total length of fish (in inches) less than or equal to 80% of the tank size (in gallons). A tank without decorations can contain a total length of fish up to 100% of the tank size.

For example:
A 10 gallon tank with decorations can hold up to 8 inches of fish, for example 4 x 2-inch-long fish.
A 20 gallon tank without decorations can hold up to 20 inches of fish, for example 6 x 1-inch-long fish and 2 x 2-inch-long fish.
fitMoreFish function
Create a function that takes these arguments:

tankSize (in gallons)
currentFish (a list of Ints representing the length of each fish currently in the tank)
fishSize (the length of the new fish we want to add to the tank)
hasDecorations (true if the the tank has decorations, false if not)
You can assume that typically a tank has decorations, and that a typical fish is 2 inches long. That means you can set those values as default parameters.

Output
Make sure you test your code against the following calls, and that you get the correct output for each.

canAddFish(10.0, listOf(3,3,3)) ---> false
canAddFish(8.0, listOf(2,2,2), hasDecorations = false) ---> true
canAddFish(9.0, listOf(1,1,3), 3) ---> false
canAddFish(10.0, listOf(), 7, true) ---> true

fun main() {
    println(canAddFish(10.0, listOf(3,3,3)))
    println(canAddFish(8.0, listOf(2,2,2), hasDecorations = false))
    println(canAddFish(9.0, listOf(1,1,3), 3))
    println(canAddFish(10.0, listOf(), 7, true))
}

fun canAddFish(tanSize: Double, currentFish: List<Int>, fishSize: Int = 2, hasDecorations: Boolean = true)
= (tanSize * if(hasDecorations) 0.8 else 1.0) >= currentFish.sum() + fishSize
*/

/*
Practice Time
Create a program that suggests an activity based on various parameters.

Start in a new file with a main function.
From main(), create a function, whatShouldIDoToday().
Let the function have three parameters.
mood: a required string parameter
weather: a string parameter that defaults to "sunny"
temperature: an Integer parameter that defaults to 24 (Celsius).

Use a when construct to return some activities based on combinations of conditions. For example:
mood == "happy" && weather == "Sunny" -> "go for a walk"
else -> "Stay home and read."

Copy/paste your finished function into REPL, and call it with combinations of arguments. For example:
whatShouldIDoToday("sad")
> Stay home and read.
Note: Keep your work as you will do more with this code in the next practice.

Practice Time
Improve your whatShouldIDoToday() program with the new knowledge from this segment.

Add 3 more situations and activities. For example:
mood == "sad" && weather == "rainy" && temperature == 0 -> "stay in bed"
temperature > 35 -> "go swimming"
Create a single-expression function for each condition and then use it in your when expression.
Challenge
Instead of passing in the mood, get a mood string from the user.

Hint: The !! operator may come handy.

Loops
This lesson introduced the while and repeat loops. To practice using them, do the following:

Change your fortune cookie program to use repeat() instead of a for loop. What happens to the break instruction? Using the error message from the compiler, with what you've learned so far, can you think of why?
Change your fortune cookie program to use a while loop, which is the better choice when you are looping until a condition is met.

fun main() {
    print("write your mood: ")
    var mood = readLine().orEmpty()

    println(whatShouldIDoToday(mood))
}

fun getInfo() = readLine().orEmpty()

fun whatShouldIDoToday(mood: String, weather: String = "sunny", temperature: Int = 24) =  when {
    mood  == "happy" && weather == "sunny" -> "go for a walk"
    mood == "sad" && weather == "rainy" && temperature == 0 -> "stay in bed"
    temperature > 35 -> "go swimming"
    else -> "Stay home and read."
}

// Loops

// for
fun main(args: Array<String>) {
    var fortune: String
    for (i in 1..10) {
        fortune = getFortune(getBirthday())
        println("\nYour fortune is: $fortune")
        if (fortune.contains("Take it easy")) break;
    }
}

// repeat
fun main(args: Array<String>) {
    var fortune: String = ""
    repeat(10) {
        fortune = getFortune(getBirthday())
        println("\nYour fortune is: $fortune")
        if (fortune.contains("Take it easy")) return;
    }
}

// while
fun main(args: Array<String>) {
    var fortune: String = ""
    while(!fortune.contains("Take it easy")) {
        fortune = getFortune(getBirthday())
        println("\nYour fortune is: $fortune")
    }
}
*/

/*
Practice Time
You can do the following filter exercise in REPL.

Create a list of spices, as follows:
val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )
Create a filter that gets all the curries and sorts them by string length.
Hint: After you type the dot (.), IntelliJ will give you a list of functions you can apply.
Filter the list of spices to return all the spices that start with 'c' and end in 'e'. Do it in two different ways.
Take the first three elements of the list and return the ones that start with 'c'.
Note: We will be able to do a lot more interesting stuff with filters after you learn about classes and Map.

fun main() {
    val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )

    println(spices.filter {it.contains("curry")}.sortedBy { it -> it.length })

    println(spices.filter {it.startsWith('c') && it.endsWith('e') })
    println(spices.filter {it.first() == 'c'  && it.last() == 'e' })

    println(spices.take(3).filter { it.startsWith('c') })
}
 */

/*
What is the difference between

val random1 = random()
val random2 = {random()}

ans
random1은 호출할때마다 같은 값만 출력이 됨. 하지만, random2는 호출할때마다 random을 다시 실행시켜서 새로운 값이 출력됨.
ex)
fun main() {
    val random1 = random()
    val random2 = {random()}
    println(random1)
    println(random1)
    println(random1)
    println(random1)
    println("-----------")
    println(random2())
    println(random2())
    println(random2())
    println(random2())
}

결과
0.1503325958816748
0.1503325958816748
0.1503325958816748
0.1503325958816748
-----------
0.8859298010646862
0.6958935136480578
0.6363985013209945
0.7972749254873572

=> random1은 random()이 단 한번 실행되고, 그 값이 random1에 저장되어있다. 그렇기 때문에 random1을 실행시킬때 마다 저장된 값이 나오기 때문에 같은 값이 출력된다.
하지만 random2는 호출시킬때 마다 random()이 실행되기 때문에 매번 다른 값이 출력된다.
 */

/*
Practice Time: Lambdas
Create a lambda and assign it to rollDice, which returns a dice roll (number between 1 and 12).
Extend the lambda to take an argument indicating the number of sides of the dice used for the roll.
If you haven't done so, fix the lambda to return 0 if the number of sides passed in is 0.
Create a new variable, rollDice2, for this same lambda using the function type notation.

fun main() {
    //var rollDice = { Random().nextInt(12) + 1}
    var rollDice = {sides: Int ->
        if (sides == 0) 0
        else Random().nextInt(sides) + 1}

    var rollDice2: (Int) -> Int = { sides ->
        if (sides == 0) 0
        else Random().nextInt(sides) + 1
    }
}
 */

/*
Practice Time: Extra Questions
Why would you want to use the function type notation instead of just the lambda?
Create a function gamePlay() that takes a roll of the dice as an argument and prints it out.
Pass your rollDice2 function as an argument to gamePlay() to generate a dice roll every time gamePlay() is called.

fun main() {
    //var rollDice = { Random().nextInt(12) + 1}
    var rollDice = {sides: Int ->
        if (sides == 0) 0
        else Random().nextInt(sides) + 1}

    var rollDice2: (Int) -> Int = { sides ->
        if (sides == 0) 0
        else Random().nextInt(sides) + 1
    }

    gamePlay(rollDice(6))
}

fun gamePlay(dice: Int){
    println(dice)
}
 */