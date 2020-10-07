package com.gmail.maystruks08.data.mappers

import com.gmail.maystruks08.data.local.entity.MovieTable
import com.gmail.maystruks08.data.remote.pojo.MoviePojo
import com.gmail.maystruks08.domain.entities.Movie
import java.util.*

fun MoviePojo.toEntity(): Movie {
    return Movie(itemId, name, image, description, Date(time))
}

fun MovieTable.toEntity(): Movie {
    return Movie(id, name, imagePath, description, Date(time))
}

fun Movie.toTableEntity(): MovieTable {
    return MovieTable(id, name, image, description, date.time)
}
