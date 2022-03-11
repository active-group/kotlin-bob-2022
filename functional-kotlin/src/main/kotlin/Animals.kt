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

    override fun feed(): Animal =
        Parrot(this.sentence, this.weight + 1)
}

data class Snake(val length: Int, val thickness: Int): Animal {
    override fun runOver(): Animal =
        Snake(this.length, 0)

    override fun feed(): Animal =
        Snake(this.length, this.thickness + 1)
}

val dillo1 = Armadillo(Alive, 10)
val dillo2 = Armadillo(Dead, 8)

fun runOverArmadillo(dillo: Armadillo): Armadillo =
    Armadillo(Dead, dillo.weight)


fun runOverAnimal(animal: Animal): Animal =
    when (animal) {
        is Armadillo -> {
            val armadillo: Armadillo = animal
            runOverArmadillo(armadillo)
        }
        is Parrot -> Parrot("", animal.weight)
        is Snake -> Snake(animal.length, 0)
    }

fun Animal.stuff(): Animal =
    when (this) {
        is Armadillo -> Armadillo(this.liveness, this.weight * 2)
        is Parrot -> Parrot(this.sentence, this.weight * 2)
        is Snake -> Snake(this.length, this.thickness * 2)
    }