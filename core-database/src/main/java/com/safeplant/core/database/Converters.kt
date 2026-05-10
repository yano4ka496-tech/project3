package com.safeplant.core.database

import androidx.room.TypeConverter

/**
 * Конвертеры для Room для преобразования типов данных
 */
class Converters {
    /**
     * Конвертер Long в Long (для совместимости с Date)
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Long? = value

    /**
     * Конвертер Long в Long (для совместимости с Date)
     */
    @TypeConverter
    fun dateToTimestamp(date: Long?): Long? = date
}