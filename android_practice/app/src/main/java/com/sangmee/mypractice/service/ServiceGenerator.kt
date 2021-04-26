package com.sangmee.rxjavapractice.requests

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val retrofitBuilder =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    private val retrofit = retrofitBuilder.build()
    private val requestApi = retrofit.create(RequestApi::class.java)

    fun getRequestApi() =
        requestApi
}
