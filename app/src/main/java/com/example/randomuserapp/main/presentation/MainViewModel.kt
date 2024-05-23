package com.example.randomuserapp.main.presentation

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.main.data.MainRepository

class MainViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    fun init(firstRun: Boolean): Screen {
        return if (firstRun) repository.lastSavedScreen() else Screen.Empty
    }
}
