package com.apilib.response.model.cardlist

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "IdTable")
@Parcelize
data class Id(
    val name: String,
    val value: String
):Parcelable