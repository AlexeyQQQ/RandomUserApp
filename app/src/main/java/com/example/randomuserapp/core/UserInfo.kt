package com.example.randomuserapp.core

// todo map ResponseCloud to UserInfo in UserRepository
data class UserInfo(
    val imageUrl: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val phone: String
)