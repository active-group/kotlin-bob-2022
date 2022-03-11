// A list is one the following:
// - the empty list - OR -
// - a cons list consisting of the first element and a rest list
sealed interface List<out A> {// variance annotation
    // fun sum(): Int
}
object Empty : List<Nothing> {
    // override fun sum(): Int = 0
}
data class Cons<A>(val first: A, val rest: List<A>): List<A> {
    // override fun sum(): Int =
    //    this.first + this.rest.sum()
}

val list1 = Cons(5, Empty)
val list2 = Cons ("Mike", Cons("Sperber", Empty))
val list4 = Cons(2, Cons(3, Cons(5, Empty)))

fun listSum(list: List<Int>): Int =
    when (list) {
        is Empty -> 0
        is Cons -> list.first + listSum(list.rest)
    }

fun List<Int>.sum(): Int =
    when (this) {
        is Empty -> 0
        is Cons -> list.first + listSum(this.rest)
    }
