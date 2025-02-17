package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.db.DownloadDao
import com.platform.mediacenter.data.model.download.DownloadEntity
import javax.inject.Inject

class DownloadRepository @Inject constructor(private val dao: DownloadDao) {
    suspend fun insertDownloadItem(item : DownloadEntity){
        dao.insertDownloadInfo(item)
    }
}