package com.harrry.comment_it.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

    init {
        isoDateFormat.timeZone = TimeZone.getTimeZone("IST")
    }


    fun formatDate(date: Date): String {
        return isoDateFormat.format(date)
    }

}