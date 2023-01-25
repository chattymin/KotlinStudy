package BeyondTheBaisic

import java.util.*

/*
Practice Time
Let's go through an example of getting information about a book in the format of a Pair. Generally, you want information about both the title and the author, and perhaps also the year.

Let’s create a basic book class, with a title, author, and year. Of course, you could get each of the properties separately.
Create a method that returns both the title and the author as a Pair.
Create a method that returns the title, author and year as a Triple. Use the documentation to find out how to use Triple.
Create a book instance.
Print out the information about the book in a sentence, such as: “Here is your book X written by Y in Z.”

class book(
    val title: String,
    val auther: String,
    val year: String
    ){

    fun GetTitleAuther(): Pair<String,String> = Pair(title,auther)

    fun GetTitleAutherYear(): Triple<String, String, String> = Triple(title, auther, year)

    fun PrintInfo() = println("Here is your book $title written by $auther in $year.")
}

fun main() {
    val testBook: book = book("X", "Y", "Z")

    val BookPair = testBook.GetTitleAuther()
    val BookTriple = testBook.GetTitleAutherYear()

    testBook.PrintInfo()

    println("Here is your book ${BookPair.first} written by ${BookPair.second}")
    println("Here is your book ${BookTriple.first} written by ${BookTriple.second} in ${BookTriple.third}.")
}
 */

/*
Practice Time
One book is rarely alone, and one author rarely writes just one book.

Create a Set of book titles called allBooks, for example, by William Shakespeare.
Create a Map called library that associates the set of books, allBooks, to the author.
Use the collections function any() on library to see if any of the books are “Hamlet’
Create a MutableMap called moreBooks, and add one title/author to it.
Use getOrPut() to see whether a title is in the map, and if the title is not in the map, add it.

Hints:

any() is applied to a collection and takes a lambda as its argument, for example:
myList.any {it.contains(“name”)}
getOrPut() is a handy function that will check whether a key is in a map, and if it is, will return the value. Otherwise, it will add the key with the supplied value to the map.
mapOf() may come in handy.

class book(
    val title: String,
    val auther: String,
    val year: String
){

    fun GetTitleAuther(): Pair<String,String> = Pair(title,auther)

    fun GetTitleAutherYear(): Triple<String, String, String> = Triple(title, auther, year)

    fun PrintInfo() = println("Here is your book $title written by $auther in $year.")
}

fun main() {
    var allBooks = setOf("Hamlet", "book1", "book2", "booke")
    var library = mapOf("auther" to allBooks)

    println(library.any { it.value.contains("Hamlet")})

    var moreBooks = mutableMapOf<String, String>("auther" to "Hamlet")
    moreBooks.getOrPut("auther") {"Hamlet"}
    moreBooks.getOrPut("auther2") {"Hamlet2"}

    println(moreBooks)
}
*/

/*
Practice Time
Create a top-level constant for the maximum number of books a person could borrow.
Inside the Book class, create a method canBorrow() that returns true or false depending on whether a user has already borrowed the max number of books.
Create a Constants object that provides constants to the book. For this example, provide the BASE_URL for all books in the library catalog. Inside Book, add a method printUrl that creates and prints a URL composed of BASE_URL, the book title, and “.html”.
The base URL is really of interest to the Book class. As such, it makes sense to limit its scope to the Book class. Use a companion object to define the constant in Book.

const val MAX_NUM_BOOKS = 20

class book(
    val title: String,
    val auther: String,
    val year: String,
    val borrow: Int = 0
){
    companion object{
        const val BASE_URL = "https://library.com/"
    }

    fun GetTitleAuther(): Pair<String,String> = Pair(title,auther)

    fun GetTitleAutherYear(): Triple<String, String, String> = Triple(title, auther, year)

    fun PrintInfo() = println("Here is your book $title written by $auther in $year.")

    fun canBorrow(): Boolean = borrow < MAX_NUM_BOOKS

    fun printUrl() = println(
        BASE_URL
        + title + "/"
        + author + "/"
        + year + "/.html"
    )
}
 */

/*
Practice Time
It can be useful to know the weight of a book, for example, for shipping. The weight of a book can change because sometimes pages get torn, and the page count changes. While calculating the weight could be defined as a method, it’s more like a helper function. Besides, it would hurt a book's feelings to have a method that tears up its pages.

Add a mutable property pages to Book.
Create an extension function on Book that returns the weight of a book as the page count multiplied by 1.5 grams.
Create another extension, tornPages(), that takes the number of torn pages as an argument and changes the page count of the book.
Write a class Puppy with a method playWithBook() that takes a book as an argument, and removes a random number of pages from the book.
Create a puppy and give it a book to play with, until there are no more pages.
Note: If you don’t want to give your puppy a book, then create a puzzle toy class and fill it with treats.

const val MAX_NUM_BOOKS = 20
class book(
    val title: String,
    val auther: String,
    val year: String,
    val borrow: Int = 0,
    var pages: Int
){
    companion object{
        const val BASE_URL = "https://library.com/"
    }

    fun GetTitleAuther(): Pair<String,String> = Pair(title,auther)

    fun GetTitleAutherYear(): Triple<String, String, String> = Triple(title, auther, year)

    fun PrintInfo() = println("Here is your book $title written by $auther in $year.")

    fun canBorrow(): Boolean = borrow < MAX_NUM_BOOKS

    fun printUrl() = println(
        BASE_URL + title + "/"
                + auther + "/"
                + year + "/.html"
    )

    fun bookWeight() = pages * 1.5

    fun tornPages(turnPage: Int){
        pages -= turnPage
        if (pages < 0)
            pages = 0
    }

}

class Puppy{
    fun playWithBook(book: book) = book.tornPages(Random().nextInt(1,book.pages+1))
}

fun main() {
    var book: book = book("title","auther","2022",5,50)
    var puppy: Puppy = Puppy()

    while(book.pages != 0) {
        println(book.pages)
        puppy.playWithBook(book)
    }
}
 */

/*
Practice Time
Using type hierarchies with generic classes follows a pretty basic pattern that we introduced in the previous segment. There was a lot of material introducing generics, but basically, when you are building them, it boils down to the following steps:

Create a type/class hierarchy. The parent is non-specific and the sub-types/subclasses are specializations.
There is at least one shared property between the classes/types, and it has a different value depending on the subtype (otherwise, having the sub-types is pointless).
We then have a class that uses all the subtypes, and performs different actions depending on what the values of the subtype’s properties are.
Let’s put this into practice using building materials and a building that needs certain amounts of those materials.

Create a new package and file and call them Buildings.
Create a class BaseBuildingMaterial with a property numberNeeded that is set to 1. You always need 1 of the base material.
Create two subclasses, Wood and Brick. For BaseBuildingMaterial you need 4 units of wood or 8 units of brick. Now you have a type hierarchy.
Create a generic class Building that can take any building material as its argument, and only building materials.
A building always requires 100 base materials. Add a property baseMaterialsNeeded and set it to 100.
Add another property, actualMaterialsNeeded and use a one-line function to calculate this from numberNeeded of the passed-in material.
Add a method build() that prints the type and number of materials needed.

Hint: Use reflection to get the class and simple name: instance::class.simpleName
Create a main function and make a building using Wood.

If you did this correctly, running main() will print something like “400 Wood required”.
 */

/*
Practice Time
That was a lot of explanations. Fortunately, IntelliJ gives you hints as to whether something should be an in or out type in your current code.

Look at the code from the previous practice and consider whether it can be an in type or an out type.
Notice that the parameter is underlined gray, and if you hover over T, IntelliJ will suggest to make it an “out” type.
 */

/*
Practice Time
Create a generic function for type BaseBuildingMaterial and call it isSmallBuilding, which takes a Building with a building material T as an argument. If the materials needed are less than 500, print "small building", otherwise, print "large building".

Note: For this function, IntelliJ recommends not to inline the function. Generally, when you create a generic function, follow the IDE's recommendation about inlining.
 */