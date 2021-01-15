package com.apilib.response.model.cardlist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.apilib.persistence.CustomConvertors
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "card_item")
@TypeConverters(CustomConvertors::class)
@Parcelize
data class CardList(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long = 0,
    val cell: String,
    val email: String,
    val gender: String,
    val id: Id,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture
) : Parcelable {
}