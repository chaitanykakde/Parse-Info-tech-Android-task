package com.chaitany.chaitanystask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
      val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Float,
    val imageUrl: String,
    val instructor: String,
    val duration: String,
    val category: String

) : Parcelable
