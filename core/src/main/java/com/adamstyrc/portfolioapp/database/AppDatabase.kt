package com.adamstyrc.portfolioapp.database

import android.app.Application
import android.content.Context
import android.icu.text.CaseMap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adamstyrc.portfolioapp.database.converter.SkillsConverter
import com.adamstyrc.portfolioapp.database.entity.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SkillsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(application: Application): AppDatabase {
            synchronized(AppDatabase::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room
                        .databaseBuilder(
                            application,
                            AppDatabase::class.java,
                            "portfolio_db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}

