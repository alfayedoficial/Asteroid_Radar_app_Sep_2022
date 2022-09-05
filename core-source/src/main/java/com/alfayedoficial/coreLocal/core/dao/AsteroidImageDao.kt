package com.alfayedoficial.coreLocal.core.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :Dao of image_table
 * @see AsteroidImagesResponse
 * Date 19/7/2022 - 12:39 PM
 */

@Dao
interface AsteroidImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: AsteroidImagesResponse)

    @Query("SELECT * FROM image_table")
    fun getAllData(): LiveData<AsteroidImagesResponse>?

    @Query("DELETE FROM image_table")
    fun clearTable()

}