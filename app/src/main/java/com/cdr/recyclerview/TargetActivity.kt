package com.cdr.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdr.recyclerview.adapter.PersonActionListener
import com.cdr.recyclerview.adapter.PersonAdapter
import com.cdr.recyclerview.databinding.ActivityTargetBinding
import com.cdr.recyclerview.model.Person
import com.cdr.recyclerview.model.PersonListener
import com.cdr.recyclerview.model.PersonService

class TargetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTargetBinding
    private lateinit var adapter: PersonAdapter // Объект Adapter
    private lateinit var adapter2: PersonAdapter // Объект Adapter
    private val personService: PersonService // Объект PersonService
        get() = (applicationContext as App).personService
    private val personService2: PersonService // Объект PersonService
        get() = (applicationContext as App).personService2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTargetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personService.setPersons(Storage.redTeam)
        personService2.setPersons(Storage.greenTeam)

        val manager = LinearLayoutManager(this) // LayoutManager
        adapter = PersonAdapter(object : PersonActionListener { // Создание объекта
            override fun onPersonGetId(person: Person) =
                Toast.makeText(this@TargetActivity, "Persons ID: ${person.id}", Toast.LENGTH_SHORT).show()

            override fun onPersonLike(person: Person) = personService.likePerson(person)

            override fun onPersonRemove(person: Person) = personService.removePerson(person)

            override fun onPersonMove(person: Person, moveBy: Int) = personService.movePerson(person, moveBy)

        })
        personService.addListener(listener)

        val manager2 = LinearLayoutManager(this) // LayoutManager
        adapter2 = PersonAdapter(object : PersonActionListener { // Создание объекта
            override fun onPersonGetId(person: Person) =
                Toast.makeText(this@TargetActivity, "Persons ID: ${person.id}", Toast.LENGTH_SHORT).show()

            override fun onPersonLike(person: Person) = personService2.likePerson(person)

            override fun onPersonRemove(person: Person) = personService2.removePerson(person)

            override fun onPersonMove(person: Person, moveBy: Int) = personService2.movePerson(person, moveBy)

        })
        personService2.addListener(listener2)

        binding.redRecyclerView.layoutManager = manager // Назначение LayoutManager для RecyclerView
        binding.redRecyclerView.adapter = adapter // Назначение адаптера для RecyclerView
        binding.greenRecyclerView.layoutManager = manager2 // Назначение LayoutManager для RecyclerView
        binding.greenRecyclerView.adapter = adapter2 // Назначение адаптера для RecyclerView
    }

    private val listener: PersonListener = {adapter.data = it}
    private val listener2: PersonListener = {adapter2.data = it}
}