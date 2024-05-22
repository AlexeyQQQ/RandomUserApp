package com.example.randomuserapp.core

import androidx.lifecycle.ViewModel

interface ManageViewModels : ProvideViewModel, ClearViewModel

interface ClearViewModel {
    fun clear(clazz: Class<out ViewModel>)
}

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clazz: Class<T>): T
}