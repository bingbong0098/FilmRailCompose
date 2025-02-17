package com.platform.mediacenter.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.platform.mediacenter.data.model.download.DownloadEntity

@Dao
interface DownloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadInfo(download: DownloadEntity)

    @Update
    suspend fun updateDownload(downloadInfo: DownloadEntity)

    @Delete
    suspend fun deleteDownload(downloadInfo: DownloadEntity)

    @Query("DELETE FROM DOWNLOAD_DATABASE_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM DOWNLOAD_DATABASE_TABLE WHERE `downloadId`=:id")
    fun getDownloadById(id: Long): DownloadEntity?

    @Query("SELECT * FROM DOWNLOAD_DATABASE_TABLE")
    fun getAllDownloads(): LiveData<List<DownloadEntity?>?>?
}