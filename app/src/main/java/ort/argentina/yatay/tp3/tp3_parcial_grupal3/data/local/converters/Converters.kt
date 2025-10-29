package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.converters

import androidx.room.TypeConverter
import java.util.Date

/**
 * Type Converters para Room
 * Permiten almacenar tipos complejos en la base de datos
 * Room no sabe cómo guardar Date directamente, así que lo convertimos a Long
 */
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

/**
 * Converter para listas de Strings
 * Útil para guardar arrays en una columna
 */
class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}

