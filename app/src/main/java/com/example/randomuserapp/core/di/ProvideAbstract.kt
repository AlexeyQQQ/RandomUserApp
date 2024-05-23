package com.example.randomuserapp.core.di

import androidx.lifecycle.ViewModel

abstract class ProvideAbstract(
    protected val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out ViewModel>
) : ProvideViewModel {

    abstract fun module(): Module<out ViewModel>

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return if (clazz == viewModelClass) {
            module().viewModel() as T
        } else {
            nextChain.viewModel(clazz)
        }
    }
}