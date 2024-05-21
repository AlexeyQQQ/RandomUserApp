package com.example.randomuserapp.load.views.error

import android.view.View
import java.io.Serializable

interface ErrorUiState : Serializable {

    fun show(updateErrorText: UpdateErrorText)

    class Show(private val message: String) : ErrorUiState {

        override fun show(updateErrorText: UpdateErrorText) {
            updateErrorText.updateUi(message = message, visibility = View.VISIBLE)
        }
    }

    object Hide : ErrorUiState {

        override fun show(updateErrorText: UpdateErrorText) {
            updateErrorText.updateUi(message = "", visibility = View.GONE)
        }
    }
}
