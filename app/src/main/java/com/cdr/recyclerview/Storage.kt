package com.cdr.recyclerview

import android.graphics.Bitmap
import com.cdr.recyclerview.model.Person
import com.cdr.recyclerview.model.PersonService

object Storage {

    /**
     * Это должен быть класс хранилища, который инициализируется при старте приложения,
     * и который прокидывается во ViewModels фрагментов.
     *
     * Откуда фрагменты смогут подписыватсья на обновление значения этой картинки. Например.
     */
    var bitmap: Bitmap? = null
    var redTeam  = mutableListOf<Person>()
    var greenTeam  = mutableListOf<Person>()
}