package com.eland.mykotlin.sample.utils

import android.os.Environment.*
import java.io.File

/**
 * Created by liuwenbin on 17/12/21.
 */

class GetCreateFilePath {

    companion object {
        fun getPath() : File? {
            var filePath: File? = null

            var fileExsit: Boolean = getExternalStorageDirectory().equals(MEDIA_MOUNTED)

            if (fileExsit) {
                filePath = File(getExternalStorageDirectory().absolutePath + "/myKotlin")
            }

            return filePath
        }
    }
}