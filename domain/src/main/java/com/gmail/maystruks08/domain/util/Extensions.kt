package com.gmail.maystruks08.domain.util

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "dd.MM.yyyy"

const val TIME_FORMAT = "HH:mm:ss"

const val DATA_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"

const val DATA_TIME_SHORT_FORMAT = "E, HH:mm:ss"

fun Date.toTimeFormat(): String = SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(this)
fun Date.toDateFormat(): String = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(this)
fun Date.toDateTimeFormat(): String = SimpleDateFormat(DATA_TIME_FORMAT, Locale.getDefault()).format(this)
fun Date.toDateTimeShortFormat(): String = SimpleDateFormat(DATA_TIME_SHORT_FORMAT, Locale.getDefault()).format(this)





