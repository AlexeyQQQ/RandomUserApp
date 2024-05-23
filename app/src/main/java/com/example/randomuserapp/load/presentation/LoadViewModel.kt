package com.example.randomuserapp.load.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.randomuserapp.load.data.LoadRepository

class LoadViewModel(
    private val uiObservable: UiObservable,
    private val repository: LoadRepository,
    private val runAsync: RunAsync,
) : AbstractViewModel(runAsync) {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            repository.saveLastScreen()
            uiObservable.updateUiState(LoadUiState.Progress)
            runAsync.runAsync(repository::load) { loadResult ->
                if (loadResult.isSuccess()) {
                    uiObservable.updateUiState(LoadUiState.Success)
                } else {
                    uiObservable.updateUiState(LoadUiState.Error(loadResult.message()))
                }
            }
        }
    }

    fun retry() {
        init(true)
    }

    fun startUpdates(showUi: (LoadUiState) -> Unit) {
        uiObservable.updateObserver(showUi)
    }

    fun stopUpdates() {
        uiObservable.clearObserver()
    }
}

abstract class AbstractViewModel(
    private val runAsync: RunAsync,
) : ViewModel(), RunAsync {

    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
        runAsync.runAsync(background, ui)
    }
}

interface RunAsync {

    fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit)

    class Base : RunAsync {
        override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
            Thread {
                val result: T = background.invoke()
                Handler(Looper.getMainLooper()).post {
                    ui.invoke(result)
                }
            }.start()
        }
    }
}
