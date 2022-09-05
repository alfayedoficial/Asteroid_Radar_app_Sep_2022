package com.alfayedoficial.coreModel.local

import android.os.Parcelable
import androidx.room.*
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.AsteroidResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.CloseApproachDataItem
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.EstimatedDiameter
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.Links
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :Entity of AsteroidImage
 * @see AsteroidResponse
 * Date 5/9/2022 - 12:39 PM
 */
@Parcelize
@Entity(tableName = "asteroid_table")
data class AsteroidEarth(
    @PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "asteroidLocal")                           var idLocal: Long? = null,
    @ColumnInfo(name = "asteroidName")                            var name: String? = null,
    @ColumnInfo(name = "asteroidId")                              var id: String? = null,
    @ColumnInfo(name = "asteroidAbsoluteMagnitudeH")              var absoluteMagnitudeH: Double? = null,
    @ColumnInfo(name ="asteroidEstimatedDiameter") 			      var estimatedDiameter: Double? = null,
    @ColumnInfo(name ="asteroidRelativeVelocity")                 var relativeVelocity: Double? = null,
    @ColumnInfo(name ="asteroidDistanceFromEarth")                var distanceFromEarth: Double? = null,
    @ColumnInfo(name ="asteroidIsPotentiallyHazardousAsteroid")   var isPotentiallyHazardousAsteroid: Boolean? = null,
    @ColumnInfo(name = "asteroidDate")                            var asteroidDate: String? = null,

    ): Parcelable