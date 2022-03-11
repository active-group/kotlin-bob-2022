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

// data classes: immutable, you equality, hashing
data class Armadillo(val liveness: Liveness, val weight: Int): Animal
