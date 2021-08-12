package com.example.scoremyessay.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevel
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOption
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class UserPreferences(context : Context) {
    private val appContext = context.applicationContext

    val accessToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    val refreshToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }

    val orderStatus: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ORDER_STATUS]
        }

    val orderOption: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ORDER_OPTION]
        }
    val orderLevel: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ORDER_LEVEL]
        }

    val orderType: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ORDER_TYPE]
        }

    suspend fun saveOrderStatus(liStatus : List<OrderStatus>){
        appContext.dataStore.edit{
                preferences ->
            run {
                preferences[ORDER_STATUS] = Gson().toJson(liStatus)
            }
        }
    }

    suspend fun saveOrderOption(liOption: List<OrderOption>){
        appContext.dataStore.edit{
            preferences ->
            run {
                preferences[ORDER_OPTION] = Gson().toJson(liOption)
            }
        }
    }

    suspend fun saveOrderType(liType : List<OrderType>){
        appContext.dataStore.edit{
            preferences ->
            run {
                preferences[ORDER_TYPE] = Gson().toJson(liType)
            }
        }
    }

    suspend fun saveOrderLevel(liLevel: List<OrderLevel>){
        appContext.dataStore.edit{
            preferences ->
            run {
                preferences[ORDER_LEVEL] = Gson().toJson(liLevel)
            }
        }
    }

    suspend fun saveAccessTokens(accessToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")

        private val ORDER_STATUS = stringPreferencesKey("order_status")
        private val ORDER_OPTION = stringPreferencesKey("order_option")
        private val ORDER_TYPE = stringPreferencesKey("order_type")
        private val ORDER_LEVEL = stringPreferencesKey("order_level")
    }
}