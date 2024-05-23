package com.example.randomuserapp.load.data

interface LoadResult {

    fun isSuccess(): Boolean

    fun message(): String

    object Success : LoadResult {

        override fun isSuccess(): Boolean = true

        override fun message(): String = throw IllegalStateException("Success message cannot exist")

    }

    class Error(private val message: String) : LoadResult {

        override fun isSuccess(): Boolean = false

        override fun message(): String = message
    }
}