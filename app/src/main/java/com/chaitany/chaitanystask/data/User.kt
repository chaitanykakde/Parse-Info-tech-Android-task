package com.chaitany.chaitanystask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val fullName: String,
    val email: String,
    val contactNo: String,
    val gender: String,
    val password: String
) : Parcelable
