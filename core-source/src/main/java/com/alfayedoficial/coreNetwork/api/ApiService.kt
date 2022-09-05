package com.alfayedoficial.coreNetwork.api

import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.AsteroidResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {


    /**-----------------------   POST   ---------------------*/


    /**-----------------------   Get   ---------------------*/

    @GET
    suspend fun getAsteroidData(
        @Url url: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Response<AsteroidResponse>

    @GET
    suspend fun getAsteroidImages(
        @Url url: String,
        @Query("api_key") apiKey: String
    ): Response<AsteroidImagesResponse>


}