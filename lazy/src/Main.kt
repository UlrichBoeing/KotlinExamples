class Person(val name: String) {
    init {
        println("Person $name wird erstellt.")
    }
}
class Demo(val name: String) {

    val person: Person by lazy { createPerson(name) }
//    val person: Person = Person("Ulrich")
}

fun main() {
    println("Start")
    val demo = Demo("Ulrich")
    println("Nach Erstellung von Demo-Objekt.")
    println(demo.person.name)
}

fun createPerson(name: String): Person {
    println("Create Person wird aufgerufen.")
    return Person(name)
}