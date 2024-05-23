package com.example.randomuserapp.load.presentation

interface UpdateUi {

    fun updateUiState(uiState: LoadUiState)
}

interface UpdateObserver {

    fun updateObserver(observer: (LoadUiState) -> Unit)

    fun clearObserver()
}

interface UiObservable : UpdateUi, UpdateObserver {

    class Base : UiObservable {

        private var observerShowUi: ((LoadUiState) -> Unit)? = null
        private var cachedUiState: LoadUiState? = null

        override fun updateUiState(uiState: LoadUiState) {
            if (observerShowUi != null) {
                observerShowUi!!.invoke(uiState)
            } else {
                cachedUiState = uiState
            }
        }

        override fun updateObserver(observer: (LoadUiState) -> Unit) {
            observerShowUi = observer
            if (cachedUiState != null) {
                observer.invoke(cachedUiState!!)
                cachedUiState = null
            }
        }

        override fun clearObserver() {
            observerShowUi = null
        }
    }
}
