package com.platform.mediacenter.data.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)
    suspend fun getBoolean(key: String): Boolean?
    suspend fun getString(key: String): String?
    suspend fun getInt(key: String): Flow<Int?>

}