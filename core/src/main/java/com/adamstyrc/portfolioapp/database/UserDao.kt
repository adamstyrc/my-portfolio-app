package com.adamstyrc.portfolioapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adamstyrc.portfolioapp.database.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("select * from User where id = 0")
    fun loadUser(): LiveData<User>
}