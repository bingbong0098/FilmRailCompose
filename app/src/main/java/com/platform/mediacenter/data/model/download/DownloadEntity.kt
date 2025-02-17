package com.platform.mediacenter.data.model.download

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.platform.mediacenter.utils.Constants.DOWNLOAD_TABLE

@Entity(tableName = DOWNLOAD_TABLE)
data class DownloadEntity(
    @PrimaryKey
    var downloadId: Int = 0,
    val fileName: String? = null,
    val percentage: Int = 0

)
