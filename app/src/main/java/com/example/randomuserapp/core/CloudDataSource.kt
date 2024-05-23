package com.example.randomuserapp.core

import retrofit2.Call
import java.net.UnknownHostException


interface CloudDataSource {

    fun data(): CloudResponse

    class Base(
        private val service: UserInfoService,
    ) : CloudDataSource {

        override fun data(): CloudResponse {
            try {
                val data: Call<CloudResponse> = service.data()
                return data.execute().body()!!
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    throw IllegalStateException("No internet connection")
                else
                    throw IllegalStateException("unknown error")
            }
        }
    }
}
