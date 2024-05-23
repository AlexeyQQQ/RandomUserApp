package com.example.randomuserapp.user.views.text

import android.content.Context
import android.util.AttributeSet

class CustomTextView : androidx.appcompat.widget.AppCompatTextView, UpdateText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    override fun updateText(text: String) {
        setText(text)
    }

    override fun getFreezesText(): Boolean {
        return true
    }
}

interface UpdateText {

    fun updateText(text: String)
}