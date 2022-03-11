// A list is one the following:
// - the empty list - OR -
// - a cons list consisting of the first element and a rest list
sealed interface List<A>
object Empty : List<Nothing>
data class Cons<A>(val first: A, val rest: List<A>): List<A>

val list1 = Cons(5, Empty)
val list2 = Cons ("Mike", Cons("Sperber", Empty))