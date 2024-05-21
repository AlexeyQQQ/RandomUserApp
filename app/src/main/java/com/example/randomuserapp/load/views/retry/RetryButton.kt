package com.example.randomuserapp.load.views.retry

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class RetryButton : MaterialButton, UpdateRetryButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    private lateinit var uiState: RetryUiState

    override fun updateUiState(outer: RetryUiState) {
        uiState = outer
        uiState.show(this)
    }

    override fun updateUi(visibility: Int) {
        this.visibility = visibility
    }

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState().let {
            val state = RetrySavedState(it)
            state.save(uiState)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as RetrySavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateUiState(restoredState.restore())
    }
}

interface UpdateRetryButton {

    fun updateUiState(outer: RetryUiState)

    fun updateUi(visibility: Int)
}