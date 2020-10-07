package com.gmail.maystruks08.filmviewer.ui.viewmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieItemView(
    val id: Int,
    val name: String,
    val imageLink: String? = null,
    val date: String
) : Parcelable