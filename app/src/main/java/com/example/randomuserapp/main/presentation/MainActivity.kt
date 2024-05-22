package com.example.randomuserapp.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.randomuserapp.R
import com.example.randomuserapp.core.ManageViewModels
import com.example.randomuserapp.databinding.ActivityMainBinding
import com.example.randomuserapp.load.presentation.LoadNavigation
import com.example.randomuserapp.load.presentation.LoadScreen
import com.example.randomuserapp.user.presentation.UserNavigation
import com.example.randomuserapp.user.presentation.UserScreen

class MainActivity : AppCompatActivity(), Navigation, ManageViewModels {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java)

        val currentScreen: Screen = viewModel.init(savedInstanceState == null)
        navigate(currentScreen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return (application as ManageViewModels).viewModel(clazz)
    }

    override fun clear(clazz: Class<out ViewModel>) {
        (application as ManageViewModels).clear(clazz)
    }
}

interface Navigation : LoadNavigation, UserNavigation {

    fun navigate(screen: Screen)

    override fun navigateFromLoadToUser() {
        navigate(UserScreen)
    }

    override fun navigateFromUserToLoad() {
        navigate(LoadScreen)
    }
}

interface Screen {

    fun show(containerId: Int, fragmentManager: FragmentManager) = Unit

    object Empty : Screen

    abstract class Replace : Screen {

        abstract fun fragment(): Fragment

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment())
                .commit()
        }
    }
}