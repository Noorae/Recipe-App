package com.project.recipeapplication.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.Tag

/**
 * Type converters for converting custom data types to and from JSON strings for Room database persistence.
 */
class Converters {

    /**
     * Converts a list of [Ingredient] objects to a JSON string.
     */
    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.toJson(value, type)
    }

    /**
     * Converts a JSON string to a list of [Ingredient] objects.
     */
    @TypeConverter
    fun toIngredientList(value: String): List<Ingredient> {
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, type)
    }

    /**
     * Converts a list of [InstructionStep] objects to a JSON string.
     */
    @TypeConverter
    fun fromInstructionStepList(value: List<InstructionStep>): String {
        val gson = Gson()
        val type = object : TypeToken<List<InstructionStep>>() {}.type
        return gson.toJson(value, type)
    }

    /**
     * Converts a JSON string to a list of [InstructionStep] objects.
     */
    @TypeConverter
    fun toInstructionStepList(value: String): List<InstructionStep> {
        val gson = Gson()
        val type = object : TypeToken<List<InstructionStep>>() {}.type
        return gson.fromJson(value, type)
    }

    /**
     * Converts a list of [Tag] objects to a JSON string.
     */
    @TypeConverter
    fun fromTagList(value: List<Tag>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.toJson(value, type)
    }

    /**
     * Converts a JSON string to a list of [Tag] objects.
     */
    @TypeConverter
    fun toTagList(value: String): List<Tag> {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, type)
    }
}