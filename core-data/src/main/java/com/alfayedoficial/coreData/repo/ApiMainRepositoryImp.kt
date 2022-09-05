package com.alfayedoficial.coreData.repo

import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.AsteroidResponse
import com.alfayedoficial.coreModel.dataState.DataState
import com.alfayedoficial.coreModel.domain.repo.ApiMainRepository
import com.alfayedoficial.coreNetwork.api.ApiService
import com.alfayedoficial.coreNetwork.interactor.NetworkCall.handleAsteroidImageApi
import com.alfayedoficial.coreNetwork.interactor.NetworkCall.handleAsteroidListApi
import com.alfayedoficial.coreNetwork.utilities.NetworkUtil
import com.alfayedoficial.kotlinutils.KUExtensionsApp.kuLocale
import com.alfayedoficial.kotlinutils.KUPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class ApiMainRepositoryImp @Inject constructor(): ApiMainRepository {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var  appPreferences: KUPreferences

    @Inject
    @Named("apiKey")
    lateinit var apiKey: String

    override fun requestAsteroidData(startDate: String, endDate: String): Flow<DataState<AsteroidResponse>> = handleAsteroidListApi(kuLocale(appPreferences)) {
        apiService.getAsteroidData(NetworkUtil.getApiLink(NetworkUtil.NetworkLinks.GetAsteroidData.type) , startDate = startDate , endDate = endDate , apiKey = apiKey)
    }
    override fun requestAsteroidImages(): Flow<DataState<AsteroidImagesResponse>> = handleAsteroidImageApi(kuLocale(appPreferences)) {
        apiService.getAsteroidImages(NetworkUtil.getApiLink(NetworkUtil.NetworkLinks.GetAsteroidImages.type) , apiKey = apiKey)
    }


}