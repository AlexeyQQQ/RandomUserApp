package com.example.randomuserapp.load.views.retry

import android.view.View
import java.io.Serializable

interface RetryUiState : Serializable {

    fun show(updateRetryButton: UpdateRetryButton)

    object Show : RetryUiState {

        override fun show(updateRetryButton: UpdateRetryButton) {
            updateRetryButton.updateUi(visibility = View.VISIBLE)
        }
    }

    object Hide : RetryUiState {

        override fun show(updateRetryButton: UpdateRetryButton) {
            updateRetryButton.updateUi(visibility = View.GONE)
        }
    }
}
