package com.example.randomuserapp.user.presentation

import androidx.fragment.app.Fragment
import com.example.randomuserapp.main.presentation.Screen

object UserScreen : Screen.Replace() {

    override fun fragment(): Fragment = UserFragment()
}