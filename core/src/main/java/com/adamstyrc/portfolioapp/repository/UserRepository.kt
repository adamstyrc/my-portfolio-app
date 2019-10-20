package com.adamstyrc.portfolioapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.adamstyrc.network.GithubApi
import com.adamstyrc.portfolioapp.database.UserDao
import com.adamstyrc.portfolioapp.database.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val userDao: UserDao
) {

    val user: LiveData<User> by lazy<LiveData<User>>(LazyThreadSafetyMode.NONE) {
        Transformations.map(userDao.loadUser()) { it }
    }

    suspend fun updateUser() {
        withContext(Dispatchers.IO) {
            val result = githubApi.getUserDescription()
            if (result.isSuccessful) {
                val userDescription = result.body()!!
                userDao.updateUser(User.fromUserDescription(userDescription))
            } else {
                throw IOException("Could not update user.")
            }
        }
    }
}