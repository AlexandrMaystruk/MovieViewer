package com.gmail.maystruks08.filmviewer.ui.viewmodels

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieFullInfoView(
    val id: Int,
    val name: String,
    val imageBitmap: Bitmap?,
    val description: String,
) : Parcelable