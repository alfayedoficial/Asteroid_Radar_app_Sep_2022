package com.alfayedoficial.coreLocal.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alfayedoficial.coreLocal.core.dao.AsteroidEarthDao
import com.alfayedoficial.coreLocal.core.dao.AsteroidImageDao
import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.NearEarth
import com.alfayedoficial.coreModel.local.AsteroidEarth
import com.alfayedoficial.coreModel.typeConverters.CloseApproachDataItemTypeConverter

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        AsteroidImagesResponse::class,
        AsteroidEarth::class,
    ]
)
@TypeConverters(
    value = [
        CloseApproachDataItemTypeConverter::class,
    ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidImageDao(): AsteroidImageDao
    abstract fun asteroidEarthDao(): AsteroidEarthDao
}
