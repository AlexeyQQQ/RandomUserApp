package com.example.randomuserapp.load.presentation

import androidx.fragment.app.Fragment
import com.example.randomuserapp.main.presentation.Screen

object LoadScreen : Screen.Replace() {

    override fun fragment(): Fragment = LoadFragment()
}