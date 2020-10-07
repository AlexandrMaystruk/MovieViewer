package com.gmail.maystruks08.filmviewer.ui.viewmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieFullInfoView(
    val id: Int,
    val name: String,
    val imageLink: String? = null,
    val description: String,
    val date: String
) : Parcelable