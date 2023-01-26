/*
Practice Time
In this practice, you are going to write the the first part of a higher-order functions game. You will implement everything, except the higher-order functions. Let’s get started.

Create a new file.
Create an enum class, Directions, that has the directions NORTH, SOUTH, EAST and WEST, as well as START, and END.
Create a class Game.
Inside Game, declare a var, path, that is a mutable list of Direction. Initialize it with one element, START.
Create 4 lambdas, north, south, east, and west, that add the respective direction to the path.
Add another lambda, end, that:
Adds END to path
Prints “Game Over”
Prints the path
Clears the path
Returns false
Create a main function.
Inside main(), create an instance of Game.
To test your code so far, in main() print the path, then invoke north, east, south, west, and end. Finally, print the path again.
You should see this output:

> [START]
Game Over: [START, NORTH, SOUTH, EAST, WEST, END]
[]
You will finish your game as the last practice in this course.

enum class Direction(){
    NORTH, SOUTH, EAST, WEST,
    START, END
}

class Game{
    var path = mutableListOf<Direction>(Direction.START)
    val north = { path.add(Direction.NORTH) }
    val south = { path.add(Direction.SOUTH) }
    val east = { path.add(Direction.EAST) }
    val west = { path.add(Direction.WEST) }
    val end = {
        path.add(Direction.END);
        println("Game Over: $path");
        path.clear();
        false }
}

fun main() {
    var game = Game()
    println(game.path)
    game.north()
    game.south()
    game.east()
    game.west()
    game.end()
    println(game.path)
}
 */

/*
Practice Time
Create an extension on List using a higher order function that returns all the numbers in the list that are divisible by 3. Start by creating an extension function on List that takes an lambda on Int and applies it to each item in the list. When the lambda returns zero, include the item in the output. For example, this list:

val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
Should return

> [3, 6, 9, 0]


fun main() {
    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    println(numbers.filter { it % 3 ==0 })
}
 */

/*
Practice Time
In this practice, you will finish your simple game using higher-order functions, that is, a function that takes functions as an argument.

In the game class, create a function move() that takes an argument called where, which is a lambda with no arguments that returns Unit.

Hint: Declaring a function that takes a lambda as its argument:

fun move(where: () -> Boolean )
Inside move(), invoke the passed-in lambda.
In the Game class, create a function makeMove() that takes a nullable String argument and returns nothing.
Inside makeMove, test whether the String is any of the 4 directions and invoke move() with the corresponding lambda. Otherwise, invoke move() with end.

Hint: You can call the function like this:

move(north)
In main() add a while loop that is always true.

Inside the loop, print instructions to the player:
print("Enter a direction: n/s/e/w:")
Call makeMove() with the contents of the input from the user via readLine()
Remove the code for testing the first version of your game.
Run your program.
Challenge:
Create a simple “map” for your game, and when the user moves, show a description of their location. Consider the following:

Use a Location class that takes a default width and height to track location. 4x4 is pretty manageable.
You can create a matrix like this:
val map = Array(width) { arrayOfNulls<String>(height) }
Use an init block to initialize your map with descriptions for each location.
When you move() also updateLocation(). There is some math involved to prevent null-pointer exceptions and keep the user from walking off the map. rem() and absoluteValue come handy.
When you are done, zip up the code and send it to a friend to try your first Kotlin game.
 */

enum class Direction(){
    NORTH, SOUTH, EAST, WEST,
    START, END
}

class Game{
    var path = mutableListOf<Direction>(Direction.START)
    val north = { path.add(Direction.NORTH) }
    val south = { path.add(Direction.SOUTH) }
    val east = { path.add(Direction.EAST) }
    val west = { path.add(Direction.WEST) }
    val end = {
        path.add(Direction.END);
        println("Game Over: $path");
        path.clear();
        false }

    fun move(where: () -> Boolean){
        where()
    }

    fun makeMove(command: String?) {

        when(command){
            "n" -> move(north)
            "s" -> move(south)
            "e" -> move(east)
            "w" -> move(west)
            else -> move(end)
        }

        /*
        when(command){
            "n" -> north
            "s" -> south
            "e" -> east()
            "w" -> west()
            else -> end()
        }
        */
    }
}

class Map(width: Int = 4, height: Int = 4){
    private var location = arrayOf(2, 2)

    fun updateLocation(direction: String?): Boolean {
        val newLocation =  location.copyOf()

        when (direction) {
            "n" -> newLocation[0] += 1
            "e" -> newLocation[1] += 1
            "s" -> newLocation[0] -= 1
            "w" -> newLocation[1] -= 1
        }

        if (isInside(newLocation[0], newLocation[1])) {
            location = newLocation
            printLocation()
            return true
        }

        println("Oops! You cannot move outside the map!")
        return false
    }

    private fun printLocation() {
        println("X: ${location[0]}, Y: ${location[1]}")
    }

    private fun isInside(x: Int, y: Int): Boolean {
        if (x < 1 || x > 4 || y < 1 || y > 4) {
            return false
        }
        return true
    }
}

fun main() {
    val game = Game()
    val map = Map()
    var bool = true

    while (bool) {
        print("Enter a direction: n/s/e/w: ")
        val direction = readLine().toString()
        val bool = map.updateLocation(direction)
        if(bool) game.makeMove(direction)
    }

    game.end()
}