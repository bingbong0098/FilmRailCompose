package com.platform.mediacenter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.platform.mediacenter.data.model.download.DownloadEntity

@Database(entities = [DownloadEntity::class], version = 1, exportSchema = false)
abstract class DownloadDatabase :RoomDatabase() {
    abstract fun dao() : DownloadDao
}