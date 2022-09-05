package com.alfayedoficial.coreData.repo

import androidx.lifecycle.LiveData
import com.alfayedoficial.coreLocal.core.AppDatabase
import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.NearEarth
import com.alfayedoficial.coreModel.domain.repo.DBMainRepository
import com.alfayedoficial.coreModel.local.AsteroidEarth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : DBMainRepositoryImp
 * @see DBMainRepository
 * Date 29/8/2022 - 8:08 PM
 */
class DBMainRepositoryImp @Inject constructor() : DBMainRepository {

    @Inject
    lateinit var appDatabase: AppDatabase

    override suspend fun insertImage(item: AsteroidImagesResponse) = withContext(CoroutineScope(Dispatchers.IO).coroutineContext){
        appDatabase.asteroidImageDao().clearTable()
        appDatabase.asteroidImageDao().insertItem(item)
    }

    override suspend fun getAsteroidImage(): LiveData<AsteroidImagesResponse>? = withContext(CoroutineScope(Dispatchers.IO).coroutineContext){
        appDatabase.asteroidImageDao().getAllData()
    }

    override suspend fun insertAsteroidEarth(items: List<AsteroidEarth>) = withContext(CoroutineScope(Dispatchers.IO).coroutineContext){
        appDatabase.asteroidEarthDao().clearTable()
        appDatabase.asteroidEarthDao().insertItems(items)
    }

    override suspend fun getAsteroidEarth(type: Int, startDate: String?): LiveData<List<AsteroidEarth>>? = withContext(CoroutineScope(Dispatchers.IO).coroutineContext){
        when(type){
            0 -> appDatabase.asteroidEarthDao().getAllDataByWeek(startDate!!)
            1 -> appDatabase.asteroidEarthDao().getAllDataByToday(startDate!!)
            else -> appDatabase.asteroidEarthDao().getAllData()
        }
    }


}