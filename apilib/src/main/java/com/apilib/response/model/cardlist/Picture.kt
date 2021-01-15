package com.apilib.response.model.cardlist

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Picture")
@Parcelize
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
):Parcelable