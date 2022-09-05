package com.alfayedoficial.coreModel.domain.repo

import androidx.lifecycle.LiveData
import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.NearEarth
import com.alfayedoficial.coreModel.local.AsteroidEarth


/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : DBMainRepository
 * Date 29/8/2022 - 12:39 PM
 */
interface DBMainRepository {

    suspend fun insertImage(item: AsteroidImagesResponse)

    suspend fun getAsteroidImage(): LiveData<AsteroidImagesResponse>?

    suspend fun insertAsteroidEarth(items: List<AsteroidEarth>)

    suspend fun getAsteroidEarth(type: Int , startDate: String? =  null): LiveData<List<AsteroidEarth>>?

}