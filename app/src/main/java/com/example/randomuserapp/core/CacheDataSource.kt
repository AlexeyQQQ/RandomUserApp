package com.example.randomuserapp.core

import com.google.gson.Gson

interface CacheDataSource {

    fun save(response: CloudResponse)

    fun read(): CloudResponse

    class Base(
        private val stringCache: StringCache,
        private val gson: Gson,
    ) : CacheDataSource {

        override fun save(response: CloudResponse) {
            val json = gson.toJson(response)
            stringCache.save(json)
        }

        override fun read(): CloudResponse {
            val json = stringCache.read()
            return gson.fromJson(json, CloudResponse::class.java)
        }
    }
}
