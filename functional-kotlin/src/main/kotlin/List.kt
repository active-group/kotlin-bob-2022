// A list is one the following:
// - the empty list - OR -
// - a cons list consisting of the first element and a rest list
sealed interface List<A>
object Empty : List<A>