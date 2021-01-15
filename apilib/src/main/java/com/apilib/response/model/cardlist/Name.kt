package com.apilib.response.model.cardlist

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "NameTable")
@Parcelize
data class Name(
    val first: String,
    val last: String,
    val title: String
):Parcelable