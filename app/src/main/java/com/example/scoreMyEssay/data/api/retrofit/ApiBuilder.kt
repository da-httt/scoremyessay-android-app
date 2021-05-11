package com.example.scoreMyEssay.data.api.retrofit

import android.content.Context
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiBuilder(context: Context) {
    private val mainServer = "https://860de0f84325.ngrok.io/"
    val service: ApiService by lazy {
//        val logging = HttpLoggingInterceptor()
//        val gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
        Retrofit.Builder()
            .baseUrl(mainServer)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //(*)
            .client(client)
            .build().create(ApiService::class.java)
    }


}
