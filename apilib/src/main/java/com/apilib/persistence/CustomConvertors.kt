package com.apilib.persistence

import androidx.room.TypeConverter
import com.apilib.response.model.cardlist.Id
import com.apilib.response.model.cardlist.Name
import com.apilib.response.model.cardlist.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomConvertors {
    private val gson = Gson()

    @TypeConverter
    fun convertJsonToName(json: String): Name? {
        val gson = Gson()
        val type = object : TypeToken<Name>() {}.type
        return gson.fromJson<Name>(json, type)
    }

    @TypeConverter
    fun convertNameStringToObject(image: Name?): String? {
        val gson = Gson()
        val type = object : TypeToken<Name>() {

        }.type
        return gson.toJson(image, type)
    }

    @TypeConverter
    fun convertJsonToId(json: String): Id? {
        val gson = Gson()
        val type = object : TypeToken<Id>() {}.type
        return gson.fromJson<Id>(json, type)
    }

    @TypeConverter
    fun convertIdStringToObject(id: Id?): String? {
        val gson = Gson()
        val type = object : TypeToken<Id>() {

        }.type
        return gson.toJson(id, type)
    }

    @TypeConverter
    fun convertJsonToPicture(json: String): Picture? {
        val gson = Gson()
        val type = object : TypeToken<Picture>() {}.type
        return gson.fromJson<Picture>(json, type)
    }

    @TypeConverter
    fun convertPictureStringToObject(id: Picture?): String? {
        val gson = Gson()
        val type = object : TypeToken<Picture>() {

        }.type
        return gson.toJson(id, type)
    }
}