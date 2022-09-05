package com.alfayedoficial.coreLocal.core.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.NearEarth
import com.alfayedoficial.coreModel.local.AsteroidEarth

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :Dao of image_table
 * @see NearEarth
 * Date 5/9/2022 - 5:39 PM
 */

@Dao
interface AsteroidEarthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(item: List<AsteroidEarth>)

    @Query("SELECT * FROM asteroid_table")
    fun getAllData(): LiveData<List<AsteroidEarth>>?

    @Query("SELECT * FROM asteroid_table  WHERE asteroidDate >= :week ORDER by asteroidDate ASC")
    fun getAllDataByWeek(week : String): LiveData<List<AsteroidEarth>>?

    @Query("SELECT * FROM asteroid_table WHERE asteroidDate == :today ORDER by asteroidDate ASC")
    fun getAllDataByToday(today : String): LiveData<List<AsteroidEarth>>?

    @Query("DELETE FROM asteroid_table")
    fun clearTable()

}