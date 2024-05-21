package com.example.randomuserapp.load.views.progress

import android.view.View
import java.io.Serializable

interface ProgressUiState : Serializable {

    fun show(updateProgressBar: UpdateProgressBar)

    object Show : ProgressUiState {

        override fun show(updateProgressBar: UpdateProgressBar) {
            updateProgressBar.updateUi(visibility = View.VISIBLE)
        }
    }

    object Hide : ProgressUiState {

        override fun show(updateProgressBar: UpdateProgressBar) {
            updateProgressBar.updateUi(visibility = View.GONE)
        }
    }
}
