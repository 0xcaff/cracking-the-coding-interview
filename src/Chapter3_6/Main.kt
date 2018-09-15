package Chapter3_6

import java.util.*

sealed class Animal
data class Dog(val name: String) : Animal()
data class Cat(val name: String) : Animal()

class AnimalShelter {
    companion object {
        private var lastCount = 0
    }

    data class Timestamped<T>(val inner: T, val time: Int) {
        companion object {
            fun <T> make(inner: T): Timestamped<T> = Timestamped(inner, AnimalShelter.lastCount++)
        }
    }

    private val dogs = LinkedList<Timestamped<Dog>>()
    private val cats = LinkedList<Timestamped<Cat>>()


    fun enqueue(animal: Animal) {
        when (animal) {
            is Dog -> dogs.add(Timestamped.make(animal))
            is Cat -> cats.add(Timestamped.make(animal))
        }
    }

    fun dequeueDogs(): Dog {
        val dog = dogs.first.inner
        dogs.removeFirst()
        return dog
    }

    fun dequeueCat(): Cat {
        val cat = cats.first.inner
        cats.removeFirst()
        return cat
    }

    fun dequeueAny(): Animal {
        val oldestDog = dogs.first
        val oldestCat = cats.first

        return if (oldestDog.time < oldestCat.time) {
            dequeueDogs()
        } else {
            dequeueCat()
        }
    }
}

fun main(args: Array<String>) {
    val animalShelter = AnimalShelter()
    val dog1 = Dog("sean's dog")
    val cat1 = Cat("jim's cat 1")
    val cat2 = Cat("jim's cat 2")
    val dog2 = Dog("my friend's dog")

    animalShelter.enqueue(dog1)
    animalShelter.enqueue(cat1)
    animalShelter.enqueue(cat2)
    animalShelter.enqueue(dog2)

    require(animalShelter.dequeueAny() == dog1)
    require(animalShelter.dequeueAny() == cat1)

    require(animalShelter.dequeueCat() == cat2)
    require(animalShelter.dequeueDogs() == dog2)
}
