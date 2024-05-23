package com.example.randomuserapp.user.views.image

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.randomuserapp.core.PicEngine

class CustomImageView : AppCompatImageView, UpdateImageUrl {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    private var url: String = ""

    private val engine by lazy {
        (context.applicationContext as ProvidePicEngine).engine()
    }

    override fun updateImageUrl(url: String) {
        this.url = url
        engine.show(this, url)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedUrl = CustomImageViewSavedUrl(it)
            savedUrl.save(url)
            return savedUrl
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredUrl = state as CustomImageViewSavedUrl
        super.onRestoreInstanceState(restoredUrl.superState)
        updateImageUrl(restoredUrl.restore())
    }
}

interface ProvidePicEngine {
    fun engine(): PicEngine
}

interface UpdateImageUrl {
    fun updateImageUrl(url: String)
}