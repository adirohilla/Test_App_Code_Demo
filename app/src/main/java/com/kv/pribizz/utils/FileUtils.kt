package com.kv.pribizz.utils

import java.io.File

object FileUtils {
    public val File.extension: String
        get() = name.substringAfterLast('.', "")
}