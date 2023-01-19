package Classes.Spice

/*
Practice Time
Let's talk about books for a moment, those heavy tomes of paper with printed letters.

Create a class, Book, with a title and an author.
Add a method, readPage(), that increases the value of a private variable, currentPage, by 1.
Create a subclass of Book; name it eBook.
eBook also takes in a format, which defaults to "text".
In eBooks, counting words makes more sense than pages. Override the readPage() method to increase the word count by 250,
the average number of words per page from typewriter days.
 */

open class Book(var title: String, var auther: String) {
    var currentPage = 1
    open fun readPage(){
        currentPage++
    }
}

class eBook(title: String, auther: String, var format: String = "text"): Book(title, auther){
    private var wordsCount = 0
    override fun readPage() {
        wordsCount += 250
    }
}
