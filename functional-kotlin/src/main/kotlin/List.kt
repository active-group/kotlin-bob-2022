// A list is one the following:
// - the empty list - OR -
// - a cons list consisting of the first element and a rest list
sealed interface List<out A> // variance annotation
object Empty : List<Nothing>
data class Cons<A>(val first: A, val rest: List<A>): List<A>

val list1 = Cons(5, Empty)
val list2 = Cons ("Mike", Cons("Sperber", Empty))

fun listSum(list: List<Int>): Int =
    when (list) {
        is Empty -> 0
        is Cons -> list.first + listSum(list.rest)
    }