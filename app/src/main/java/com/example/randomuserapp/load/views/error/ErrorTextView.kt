package com.example.randomuserapp.load.views.error

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class ErrorTextView : MaterialTextView, UpdateErrorText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    private lateinit var uiState: ErrorUiState

    override fun updateUiState(outer: ErrorUiState) {
        uiState = outer
        uiState.show(this)
    }

    override fun updateUi(message: String, visibility: Int) {
        text = message
        this.visibility = visibility
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = ErrorSavedState(it)
            state.save(uiState)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ErrorSavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateUiState(restoredState.restore())
    }
}

interface UpdateErrorText {

    fun updateUiState(outer: ErrorUiState)

    fun updateUi(message: String, visibility: Int)
}