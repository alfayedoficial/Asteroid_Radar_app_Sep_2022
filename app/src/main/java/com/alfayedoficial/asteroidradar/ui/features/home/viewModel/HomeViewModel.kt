package com.alfayedoficial.asteroidradar.ui.features.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alfayedoficial.asteroidradar.core.common.viewModel.BaseViewModel
import com.alfayedoficial.asteroidradar.utilities.Constants
import com.alfayedoficial.asteroidradar.utilities.Constants.DEFAULT_END_DATE_DAYS
import com.alfayedoficial.asteroidradar.utilities.TemplateEnums
import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.NearEarth
import com.alfayedoficial.coreModel.dataState.DataState
import com.alfayedoficial.coreModel.domain.repo.ApiMainRepository
import com.alfayedoficial.coreModel.domain.repo.DBMainRepository
import com.alfayedoficial.coreModel.local.AsteroidEarth
import com.alfayedoficial.kotlinutils.kuInfoLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class HomeViewModel @Inject constructor( private val apiRepo: ApiMainRepository, private val dpRepo : DBMainRepository) : BaseViewModel() {

    private val _image = MutableLiveData<AsteroidImagesResponse>()
    val image: LiveData<AsteroidImagesResponse> = _image

    private val _asteroidList = MutableLiveData<List<AsteroidEarth>>()
    val asteroidList: LiveData<List<AsteroidEarth>> = _asteroidList

    private var _progressLoadingMutableLiveData = MutableLiveData<Boolean>()
    val progressLoadingLiveData = _progressLoadingMutableLiveData

    private var _errorResponseImageMutableLiveData = MutableLiveData<String>()
    val errorResponseImageLiveData = _errorResponseImageMutableLiveData

    private lateinit var startDate:String
    private lateinit var endDate:String
    private var type = TemplateEnums.Filter.SAVED.type

    init {
       initData()
        mViewModelScope.launch {
            dpRepo.getAsteroidImage()?.observeForever {
                _image.postValue(it)
            }

            filterLocalData(type)
        }
    }

    /**
     * Init data
     * Call api to get image and Asteroid data
     * Retrieving data from local database
     */
    fun initData() {
        updateDate()
        mViewModelScope.launch {
            apiRepo.requestAsteroidImages().collect {
                when (it) {
                    is DataState.ApiSuccess -> {
                        dpRepo.insertImage(it.data)
                    }
                    is DataState.ApiError -> {
                        _errorResponseImageMutableLiveData.postValue(it.exception.validationError())
                    }
                    else -> {}
                }
            }

            apiRepo.requestAsteroidData(startDate, endDate).collect {
                when (it) {
                    is DataState.IsLoading -> {_progressLoadingMutableLiveData.postValue(true)}
                    is DataState.ApiSuccess -> {
                        _progressLoadingMutableLiveData.postValue(false)
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
                    }
                    is DataState.ApiError -> {
                        _progressLoadingMutableLiveData.postValue(false)
                        _errorResponseImageMutableLiveData.postValue(it.exception.validationError())
                    }
                }
            }


        }
    }

    suspend fun filterLocalData(type: Int) {
         this.type = type
        dpRepo.getAsteroidEarth(type , startDate)?.observeForever {
            _asteroidList.postValue(it)
        }
    }

    private fun updateDate(){
        val day = Calendar.getInstance()

        val date = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        startDate = date.format(day.time)

        day.add(Calendar.DAY_OF_YEAR,DEFAULT_END_DATE_DAYS)

        val last = day.time
        endDate = date.format(last)
    }
}