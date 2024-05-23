package com.example.randomuserapp.core

import com.google.gson.annotations.SerializedName

data class CloudResponse(
    @SerializedName("results")
    val results: List<Result>,
)

data class Result(
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("name")
    val name: Name,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("phone")
    val phone: String,
)

data class Name(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
)

data class Picture(
    @SerializedName("large")
    val large: String,
)
