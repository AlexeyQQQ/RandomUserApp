package com.example.randomuserapp.user.views


import android.os.Parcel
import android.os.Parcelable
import android.view.View


class CustomImageViewSavedUrl : View.BaseSavedState {

    private lateinit var savedUrl: String

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        savedUrl = parcelIn.readString() ?: ""
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeString(savedUrl)
    }

    fun restore(): String = savedUrl

    fun save(url: String) {
        savedUrl = url
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<CustomImageViewSavedUrl> {
        override fun createFromParcel(parcel: Parcel): CustomImageViewSavedUrl =
            CustomImageViewSavedUrl(parcel)

        override fun newArray(size: Int): Array<CustomImageViewSavedUrl?> =
            arrayOfNulls(size)
    }
}