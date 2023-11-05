package com.laloushltd.giniappproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laloushltd.giniappproject.domain.model.ImageData

@Database(
    entities = [ImageData::class],
    version = 1
)
abstract class ImageDatabase: RoomDatabase() {

    abstract val imageDao: ImageDao

    companion object {
        const val DATABASE_NAME = "image_db"
    }
}