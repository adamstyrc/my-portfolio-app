package com.adamstyrc.portfolioapp.database.converter

import androidx.room.TypeConverter
import com.adamstyrc.portfolioapp.database.entity.User
import com.google.gson.Gson
import android.R.attr.data
import com.google.gson.reflect.TypeToken
import java.util.*

class SkillsConverter {

    var gson = Gson()

    @TypeConverter
    fun toSkills(string: String?): User.Skills {
        if (string == null) {
            return User.Skills()
        }

        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun fromSkills(skills: User.Skills): String {
        return gson.toJson(skills)
    }
}