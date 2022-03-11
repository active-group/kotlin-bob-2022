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

// large lists will cause stack overflow
// JVM:
// - each method call allocates on the stack
// - stack is small
fun List<Int>.sum(): Int =
    when (this) {
        is Empty -> 0
        is Cons -> this.first + listSum(this.rest)
    }

// makes the Kotlin compiler compile the recursive call to a jump
tailrec fun List<Int>.sum1(acc: Int): Int =
    when (this) {
        is Empty -> acc
        is Cons ->
            // tail call / no context
            this.rest.sum1(acc + this.first)
    }

fun <A, B> List<A>.fold(forEmpty: B, forCons: (A, B) -> B): B =
    when (this) {
        is Empty -> forEmpty
        is Cons ->
            forCons(this.first, this.rest.fold(forEmpty, forCons))
    }

fun listSum2(list: List<Int>): Int =
    // list.fold(0, { f, r -> f + r} )
    list.fold(0) { f, r -> f + r }

fun <A, B> List<A>.map(f: (A) -> B): List<B> =
    when (this) {
        is Empty -> Empty
        is Cons ->
            Cons(f(this.first), this.rest.map(f))
    }

// no higher-kinded types: can't abstract over type constructor

// suspend fun : makes the compiler do a CPS transformation / "monad"

// implement an Option datatype: a value that either contains a result of type A, or the absence of one
