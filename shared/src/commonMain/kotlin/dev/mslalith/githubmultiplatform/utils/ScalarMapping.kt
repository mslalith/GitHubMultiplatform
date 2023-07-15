package dev.mslalith.githubmultiplatform.utils

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import dev.mslalith.githubmultiplatform.extensions.toLocalDateTime
import kotlinx.datetime.LocalDateTime

object LocalDateTimeAdapter : Adapter<LocalDateTime> {
    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters): LocalDateTime {
        val isoString = reader.nextString()!!
        return isoString.toLocalDateTime()
    }

    override fun toJson(writer: JsonWriter, customScalarAdapters: CustomScalarAdapters, value: LocalDateTime) {
        writer.value(value.toString())
    }
}
