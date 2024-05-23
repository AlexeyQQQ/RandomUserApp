package com.example.randomuserapp.load.presentation

import com.example.randomuserapp.load.views.error.ErrorUiState
import com.example.randomuserapp.load.views.error.UpdateErrorText
import com.example.randomuserapp.load.views.progress.ProgressUiState
import com.example.randomuserapp.load.views.progress.UpdateProgressBar
import com.example.randomuserapp.load.views.retry.RetryUiState
import com.example.randomuserapp.load.views.retry.UpdateRetryButton

interface LoadUiState {

    fun update(
        progressBar: UpdateProgressBar,
        errorTextView: UpdateErrorText,
        retryButton: UpdateRetryButton
    ) = Unit

    fun navigate(navigate: () -> Unit) = Unit

    data class Error(
        private val message: String
    ) : LoadUiState {

        override fun update(
            progressBar: UpdateProgressBar,
            errorTextView: UpdateErrorText,
            retryButton: UpdateRetryButton
        ) {
            progressBar.updateUiState(ProgressUiState.Hide)
            errorTextView.updateUiState(ErrorUiState.Show(message = message))
            retryButton.updateUiState(RetryUiState.Show)
        }
    }

    object Progress : LoadUiState {

        override fun update(
            progressBar: UpdateProgressBar,
            errorTextView: UpdateErrorText,
            retryButton: UpdateRetryButton
        ) {
            progressBar.updateUiState(ProgressUiState.Show)
            errorTextView.updateUiState(ErrorUiState.Hide)
            retryButton.updateUiState(RetryUiState.Hide)
        }
    }

    object Success : LoadUiState {

        override fun navigate(navigate: () -> Unit) {
            navigate.invoke()
        }
    }
}
