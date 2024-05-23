package com.example.randomuserapp.core.di

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.load.di.ProvideLoadViewModel
import com.example.randomuserapp.main.di.ProvideMainViewModel
import com.example.randomuserapp.user.di.ProvideUserViewModel

interface ManageViewModels : ProvideViewModel, ClearViewModel

interface ClearViewModel {

    fun clear(clazz: Class<out ViewModel>)
}

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ManageViewModels {

        private val viewModelsMap = mutableMapOf<Class<out ViewModel>, ViewModel?>()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return if (viewModelsMap[clazz] == null) {
                val viewModel = provideViewModel.viewModel(clazz)
                viewModelsMap[clazz] = viewModel
                return viewModel
            } else {
                viewModelsMap[clazz] as T
            }
        }

        override fun clear(clazz: Class<out ViewModel>) {
            viewModelsMap[clazz] = null
        }
    }

    class Make(
        core: Core,
    ) : ProvideViewModel {

        private val chain: ProvideViewModel

        init {
            var temp: ProvideViewModel = ProvideError()
            temp = ProvideUserViewModel(core, temp)
            temp = ProvideLoadViewModel(core, temp)
            chain = ProvideMainViewModel(core, temp)
        }

        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return chain.viewModel(clazz)
        }
    }
}






