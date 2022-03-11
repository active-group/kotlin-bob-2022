// Animals on the Texas Highway

// An animal of the following:
// - an armadillo - OR -
// - an parrot
// mixed data / sum type / discriminated union

sealed interface Animal {
    fun runOver(): Animal
    fun feed(): Animal
}

// An armadillo has the following properties:
// - dead or alive - AND -
// - weight

sealed interface Liveness
object Dead : Liveness
object Alive : Liveness

typealias Weight = Int

// data classes: immutable, you equality, hashing
data class Armadillo(val liveness: Liveness, val weight: Weight): Animal {
    override fun runOver(): Animal =
        Armadillo(Dead, this.weight)

    override fun feed(): Animal =
        Armadillo(this.liveness, weight + 1)
}

// A parrott has the following properties:
// - sentence
// - weight
data class Parrot(val sentence: String, val weight: Weight): Animal {
    override fun runOver(): Animal =
        Parrot("", this.weight)
}

data class Snake(val length: Int, val thickness: Int): Animal {
    override fun runOver(): Animal =
        Snake(this.length, 0)
}

val dillo1 = Armadillo(Alive, 10)
val dillo2 = Armadillo(Dead, 8)

fun runOverArmadillo(dillo: Armadillo): Armadillo =
    Armadillo(Dead, dillo.weight)


fun runOverAnimal(animal: Animal): Animal =
    when (animal) {
        is Armadillo -> runOverArmadillo(animal)
        is Parrot -> Parrot("", animal.weight)
        is Snake -> Snake(animal.length, 0)
    }