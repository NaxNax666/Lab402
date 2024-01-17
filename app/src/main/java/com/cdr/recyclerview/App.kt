package com.cdr.recyclerview

import android.app.Application
import com.cdr.recyclerview.model.PersonService

class App : Application() {
    val personServiceInit = PersonService()
    val personService = PersonService()
    val personService2 = PersonService()
}