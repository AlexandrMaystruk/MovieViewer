package com.gmail.maystruks08.data.cache

import com.gmail.maystruks08.domain.entities.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCache @Inject constructor() {

    var defaultEntityList = mutableListOf<DefaultEntity>()

}