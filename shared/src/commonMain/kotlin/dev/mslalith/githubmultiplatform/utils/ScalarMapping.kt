package dev.mslalith.githubmultiplatform.utils

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeAdapter : Adapter<LocalDateTime> {
    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters): LocalDateTime {
        val isoString = reader.nextString()!!

        val (year, month, day) = isoString
            .substringBefore(delimiter = "T")
            .split('-')
            .map { it.toInt() }
            .let { if (it.size < 3) it + List(size = 3 - it.size) { 0 } else it }

        val (hour, minute, second) = isoString
            .substringAfter(delimiter = "T")
            .substringBefore(delimiter = "Z")
            .split(':')
            .map { it.toInt() }
            .let { if (it.size < 3) it + List(size = 3 - it.size) { 0 } else it }

        return LocalDateTime(year, month, day, hour, minute, second)
    }

    override fun toJson(writer: JsonWriter, customScalarAdapters: CustomScalarAdapters, value: LocalDateTime) {
        writer.value(value.toString())
    }
}
