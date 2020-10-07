package com.gmail.maystruks08.domain.entities

import java.util.*

data class Movie(val id: Int,
                 val name: String,
                 val image: String? = null,
                 val description: String,
                 val date: Date) {

    //TODO do any here

}

