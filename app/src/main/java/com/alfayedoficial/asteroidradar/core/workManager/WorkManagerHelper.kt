package com.alfayedoficial.asteroidradar.core.workManager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.alfayedoficial.asteroidradar.utilities.Constants
import com.alfayedoficial.coreLocal.core.AppDatabase
import com.alfayedoficial.coreModel.dataState.DataState
import com.alfayedoficial.coreModel.domain.repo.ApiMainRepository
import com.alfayedoficial.coreModel.domain.repo.DBMainRepository
import com.alfayedoficial.coreModel.local.AsteroidEarth
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/6/2022 - 4:48 AM
 */


@HiltWorker
class WorkManagerHelper @AssistedInject constructor(
    @Assisted private var appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val dpRepo: DBMainRepository,
    private val apiRepo: ApiMainRepository,
) : CoroutineWorker(appContext, workerParams) {

    companion object{
        var WORK_NAME = "AsteroidWorker"
    }

    override suspend fun doWork(): Result  = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {

        val day = Calendar.getInstance()
        val date = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val startDate = date.format(day.time)
        day.add(Calendar.DAY_OF_YEAR, Constants.DEFAULT_END_DATE_DAYS)
        val last = day.time
        val endDate = date.format(last)

        getAsteroidFromApi(startDate, endDate)
    }

    suspend fun getAsteroidFromApi(startDate: String, endDate: String) : Result = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
        var result : Result = Result.failure()
        apiRepo.requestAsteroidData(startDate, endDate).collect {
            when (it) {
                is DataState.ApiSuccess -> {
                    val newList = ArrayList<AsteroidEarth>(arrayListOf())
                    it.data.nearEarthList?.forEach { (key, value) ->
                        value.forEach { asteroid ->

                            newList.add(AsteroidEarth().apply {
                                id = asteroid.id
                                name = asteroid.name
                                absoluteMagnitudeH = asteroid.absoluteMagnitudeH
                                estimatedDiameter = asteroid.estimatedDiameter?.kilometers?.estimatedDiameterMax
                                relativeVelocity = asteroid.closeApproachData?.get(0)?.relativeVelocity?.kilometersPerSecond?.toDoubleOrNull()
                                distanceFromEarth = asteroid.closeApproachData?.get(0)?.missDistance?.astronomical?.toDoubleOrNull()
                                isPotentiallyHazardousAsteroid = asteroid.isPotentiallyHazardousAsteroid
                                asteroidDate = key
                            })
                        }
                    }
                    dpRepo.insertAsteroidEarth(newList)
                    result = Result.success()
                }
                is DataState.ApiError -> {
                    result = Result.failure()
                }
                else ->{}
            }
        }
        return@withContext result
    }

}