package com.example.scoremyessay.retrofit

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import com.example.scoremyessay.R
import com.example.scoremyessay.model.LoginRequest
import com.example.scoremyessay.model.LoginResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

object RetrofiClient {

    const val MainServer = "192.168.0.87:8000"
    val service: AppRepository by lazy {
        val logging = HttpLoggingInterceptor()
        val gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(MainServer)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //(*)
            .client(client)
            .build().create<AppRepository>(AppRepository::class.java)
    }
    interface AppRepository {
        @FormUrlEncoded
        @POST("auth")
        fun loginUser(@Body request: LoginRequest) : Call<LoginResponse>
    }
}
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}