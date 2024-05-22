package com.example.randomuserapp

import com.example.randomuserapp.load.LoadResult
import com.example.randomuserapp.load.presentation.LoadRepository
import com.example.randomuserapp.load.presentation.LoadUiState
import com.example.randomuserapp.load.presentation.LoadViewModel
import com.example.randomuserapp.load.presentation.RunAsync
import com.example.randomuserapp.load.presentation.UiObservable
import org.junit.Assert.assertEquals
import org.junit.Test


class LoadViewModelTest {

    @Test
    fun testCaseNumber1() {
        val repository = FakeLoadRepository()
        val runAsync = FakeRunAsync()
        val uiObservable = FakeUiObservable()

        val viewModel = LoadViewModel(
            uiObservable = uiObservable,
            repository = repository,
            runAsync = runAsync
        )

        // step 1 state progress
        viewModel.init(firstRun = true)
        assertEquals(LoadUiState.Progress, uiObservable.uiStateCallList[0])
        assertEquals(true, repository.lastScreenIsSaved)

        // step 2 state error
        assertEquals(LoadUiState.Error(message = "fail"), uiObservable.uiStateCallList[1])

        // step 3 state progress
        viewModel.retry()
        assertEquals(LoadUiState.Progress, uiObservable.uiStateCallList[2])
        assertEquals(LoadUiState.Success, uiObservable.uiStateCallList[3])

        // step 4 rotate screen
        assertEquals(4, uiObservable.uiStateCallList.size)
        viewModel.init(firstRun = false)
        assertEquals(4, uiObservable.uiStateCallList.size)
    }
}

private class FakeUiObservable : UiObservable {

    val uiStateCallList = mutableListOf<LoadUiState>()

    override fun updateUiState(uiState: LoadUiState) {
        uiStateCallList.add(uiState)
    }

    override fun updateObserver(observer: (LoadUiState) -> Unit) = Unit

    override fun clearObserver() = Unit
}

private class FakeRunAsync : RunAsync {

    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
        val result = background.invoke()
        ui.invoke(result)
    }
}

private class FakeLoadRepository : LoadRepository {

    var success: Boolean = false
    var lastScreenIsSaved: Boolean = false

    override fun load(): LoadResult {
        return if (success) {
            success = false
            LoadResult.Success
        } else {
            success = true
            LoadResult.Error(message = "fail")
        }
    }

    override fun saveLastScreen() {
        lastScreenIsSaved = true
    }
}