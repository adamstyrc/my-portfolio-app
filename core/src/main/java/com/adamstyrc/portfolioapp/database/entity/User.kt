package com.adamstyrc.portfolioapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.adamstyrc.network.model.UserDescription
import com.adamstyrc.portfolioapp.database.converter.SkillsConverter

@Entity
data class User(
    val name: String?,
    val skills: Skills?,
    val description: String?,
    @PrimaryKey val id: Int = 0
) {

    companion object {
        fun fromUserDescription(userDescription: UserDescription) =
            User(
                userDescription.name,
                Skills(userDescription.skills ?: ArrayList()),
                userDescription.description
            )
    }

    data class Skills(
        val skills: ArrayList<String> = ArrayList()
    )
}

