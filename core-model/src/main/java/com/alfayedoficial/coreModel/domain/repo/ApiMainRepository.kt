package com.alfayedoficial.coreModel.domain.repo

import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.AsteroidResponse
import com.alfayedoficial.coreModel.dataState.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Interface Auth
 * Date 25/8/2022 - 3:08 PM
 */
interface ApiMainRepository{

    fun requestAsteroidData(startDate: String , endDate: String) : Flow<DataState<AsteroidResponse>>

    fun requestAsteroidImages() : Flow<DataState<AsteroidImagesResponse>>

}