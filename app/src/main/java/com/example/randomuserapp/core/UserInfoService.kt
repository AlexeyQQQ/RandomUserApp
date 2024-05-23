package com.example.randomuserapp.core

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import java.net.UnknownHostException

interface UserInfoService {

    // todo retrofit https://randomuser.me/api/

    @GET("api/")
    fun data(): Call<CloudResponse>
}

class MockService : UserInfoService {

    private val mockResponse = listOf(
        CloudResponse(
            listOf(
                Result(
                    Picture(""),
                    Name("Eelis", "Neva"),
                    "male",
                    "02-255-856"
                )
            )
        ),
        CloudResponse(
            listOf(
                Result(
                    Picture(""),
                    Name("Abbie", "Chavez"),
                    "female",
                    "015242 77010"
                )
            )
        )
    )

    private var currentIndex = 0
    private var showSuccess = false

    override fun data(): Call<CloudResponse> {
        Thread.sleep(1000)
        if (showSuccess) {
            showSuccess = false
            return object : Call<CloudResponse> {

                override fun clone(): Call<CloudResponse> = this

                override fun execute(): Response<CloudResponse> {
                    if (currentIndex == 2) currentIndex = 0
                    return Response.success(mockResponse[currentIndex++])
                }

                override fun isExecuted(): Boolean = false

                override fun cancel() {}

                override fun isCanceled(): Boolean = false

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }

                override fun enqueue(callback: Callback<CloudResponse>) {
                    TODO("Not yet implemented")
                }
            }
        } else {
            showSuccess = true
            throw UnknownHostException()
        }
    }
}
