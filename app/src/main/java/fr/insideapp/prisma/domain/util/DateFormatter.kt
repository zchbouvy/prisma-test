package fr.insideapp.prisma.domain.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val simpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE) }
    private val newDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.FRANCE) }


    fun format(dateStr: String): String {
        val date = simpleDateFormat.parse(dateStr)
        return date?.let { newDateFormat.format(it) } ?: ""
    }
}