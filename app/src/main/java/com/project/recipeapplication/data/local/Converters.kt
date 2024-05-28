package com.project.recipeapplication.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.Tag

class Converters {
    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toIngredientList(value: String): List<Ingredient> {
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromInstructionStepList(value: List<InstructionStep>): String {
        val gson = Gson()
        val type = object : TypeToken<List<InstructionStep>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toInstructionStepList(value: String): List<InstructionStep> {
        val gson = Gson()
        val type = object : TypeToken<List<InstructionStep>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTagList(value: List<Tag>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTagList(value: String): List<Tag> {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, type)
    }
}