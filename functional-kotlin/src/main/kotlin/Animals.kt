// Animals on the Texas Highway

// An animal of the following:
// - an armadillo - OR -
// - an parrot
// mixed data / sum type / discriminated union

sealed interface Animal

// An armadillo has the following properties:
// - dead or alive - AND -
// - weight

sealed interface Liveness
object Dead : Liveness
object Alive : Liveness

typealias Weight = Int

// data classes: immutable, you equality, hashing
data class Armadillo(val liveness: Liveness, val weight: Weight): Animal {
    fun runOver(): Armadillo =
        Armadillo(Dead, this.weight)
}

// A parrott has the following properties:
// - sentence
// - weight
data class Parrot(val sentence: String, val weight: Weight): Animal

val dillo1 = Armadillo(Alive, 10)
val dillo2 = Armadillo(Dead, 8)

fun runOverArmadillo(dillo: Armadillo): Armadillo =
    Armadillo(Dead, dillo.weight)