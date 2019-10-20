package com.adamstyrc.portfolioapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamstyrc.network.GithubApi
import com.adamstyrc.network.model.UserDescription
import com.adamstyrc.portfolioapp.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val user = userRepository.user
    private val loading = MutableLiveData<Boolean>()
    private val snackbar = MutableLiveData<String>()

    init {
        updateProfile()
    }

    fun loading(): LiveData<Boolean> = loading

    fun snackbar(): LiveData<String> = snackbar

    fun updateProfile() {
        launchDataLoad {
            userRepository.updateUser()
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                loading.value = true
                block()
            } catch (error: Exception) {
                displayErrorMessage()
            } finally {
                loading.value = false
            }
        }
    }

    private fun displayErrorMessage() {
        snackbar.value = "Could not load user data."
    }

    fun onSnackbarShown() {
        snackbar.value = null
    }
}