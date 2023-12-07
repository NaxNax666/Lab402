package com.cdr.recyclerview.model

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

typealias PersonListener = (persons: List<Person>) -> Unit

class PersonService {

    private var persons = mutableListOf<Person>() // Все пользователи

    private var listeners = mutableListOf<PersonListener>() // Все слушатели

    init {
        val faker = Faker.instance() // Переменная для создания случайных данных

        persons = (1..10).map {
            Person(
                id = it.toLong(),
                name = faker.name().fullName(),
                isLiked = false
            )
        }.toMutableList()
    }

    fun getPersons(): List<Person> = persons
    fun setPersons(newPers: MutableList<Person>){
        persons = ArrayList(newPers) // Создаем новый список
        for (i in 0 until newPers.size) {
            persons[i] = newPers[i]
        }
    }

    fun getLikedPerson(): MutableList<Person> {
        val retpersons = mutableListOf<Person>() // Создаем новый список
        for (i in 0 until persons.size) {
            val buff = persons[i]
            if (buff.isLiked) {
                retpersons.add(buff)
            }
        }
        return retpersons
    }

    fun likePerson(person: Person) {
        val index = persons.indexOfFirst { it.id == person.id } // Находим индекс человека в списке
        if (index == -1) return // Останавливаемся, если не находим такого человека

        persons = ArrayList(persons) // Создаем новый список
        persons[index] =
            persons[index].copy(isLiked = !persons[index].isLiked) // Меняем значение "лайка" на противоположное
        notifyChanges()
    }

    fun removePerson(person: Person) {
        val index = persons.indexOfFirst { it.id == person.id } // Находим индекс человека в списке
        if (index == -1) return // Останавливаемся, если не находим такого человека

        persons = ArrayList(persons) // Создаем новый список
        persons.removeAt(index) // Удаляем человека
        notifyChanges()
    }
    fun addPerson(person: Person) {
        //val index = persons.indexOfFirst { it.id == person.id } // Находим индекс человека в списке
        //if (index == -1) return // Останавливаемся, если не находим такого человека

        persons = ArrayList(persons) // Создаем новый список
        persons.add(person) // Удаляем человека
        notifyChanges()
    }

    fun movePerson(person: Person, moveBy: Int) {
        val oldIndex =
            persons.indexOfFirst { it.id == person.id } // Находим индекс человека в списке
        if (oldIndex == -1) return // Останавливаемся, если не находим такого человека

        val newIndex =
            oldIndex + moveBy // Вычисляем новый индекс, на котором должен находится человек
        persons = ArrayList(persons) // Создаем новый список
        Collections.swap(persons, oldIndex, newIndex) // Меняем местами людей
        notifyChanges()
    }


    fun addListener(listener: PersonListener) {
        listeners.add(listener)
        listener.invoke(persons)
    }

    fun removeListener(listener: PersonListener) {
        listeners.remove(listener)
        listener.invoke(persons)
    }

    private fun notifyChanges() = listeners.forEach { it.invoke(persons) }

}

data class Person(
    val id: Long, // Уникальный номер пользователя
    val name: String, // Имя человека
    val isLiked: Boolean // Был ли лайкнут пользователь
)