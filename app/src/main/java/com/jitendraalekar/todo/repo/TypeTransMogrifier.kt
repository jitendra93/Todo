package com.jitendraalekar.todo.repo

import androidx.room.TypeConverter
import java.time.Instant

class TypeTransMogrifier {

    @TypeConverter
    fun fromInstant(date: Instant?): Long? = date?.toEpochMilli()

    @TypeConverter
    fun toInstant(epochInMillies: Long?): Instant? =
        epochInMillies?.let { Instant.ofEpochMilli(it) }
}