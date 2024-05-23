package com.example.randomuserapp.user.presentation

import com.example.randomuserapp.user.views.image.UpdateImageUrl
import com.example.randomuserapp.user.views.text.UpdateText

interface UserUiState {

    fun update(
        pictureImageView: UpdateImageUrl,
        firstNameTextView: UpdateText,
        lastNameTextView: UpdateText,
        genderTextView: UpdateText,
        phoneTextView: UpdateText
    ) = Unit

    object Empty : UserUiState

    data class ShowInfo(
        private val imageUrl: String,
        private val firstName: String,
        private val lastName: String,
        private val gender: String,
        private val phone: String
    ) : UserUiState {

        override fun update(
            pictureImageView: UpdateImageUrl,
            firstNameTextView: UpdateText,
            lastNameTextView: UpdateText,
            genderTextView: UpdateText,
            phoneTextView: UpdateText
        ) {
            pictureImageView.updateImageUrl(imageUrl)
            firstNameTextView.updateText(firstName)
            lastNameTextView.updateText(lastName)
            genderTextView.updateText(gender)
            phoneTextView.updateText(phone)
        }
    }
}
