package com.alfayedoficial.coreModel.apiResponse.asteroidResponses

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class AsteroidResponse(

    @field:SerializedName("near_earth_objects")
    var nearEarthList: Map<String, MutableList<NearEarth>>? = null,

    @field:SerializedName("element_count")
    val elementCount: Int? = null,

    @field:SerializedName("links")
    val links: Links? = null
)


data class NearEarth(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double? = null,

    @field:SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameter? = null,

    @field:SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean? = null,

    @field:SerializedName("is_sentry_object")
    val isSentryObject: Boolean? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nasa_jpl_url")
    val nasaJplUrl: String? = null,

    @field:SerializedName("neo_reference_id")
    val neoReferenceId: String? = null,

    @field:SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachDataItem>? = arrayListOf(),

    )

data class CloseApproachDataItem(

    @field:SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocity? = null,

    @field:SerializedName("orbiting_body")
    val orbitingBody: String? = null,

    @field:SerializedName("close_approach_date")
    val closeApproachDate: String? = null,

    @field:SerializedName("epoch_date_close_approach")
    val epochDateCloseApproach: Long? = null,

    @field:SerializedName("close_approach_date_full")
    val closeApproachDateFull: String? = null,

    @field:SerializedName("miss_distance")
    val missDistance: MissDistance? = null
)

data class RelativeVelocity(

    @field:SerializedName("kilometers_per_hour")
    val kilometersPerHour: String? = null,

    @field:SerializedName("kilometers_per_second")
    val kilometersPerSecond: String? = null,

    @field:SerializedName("miles_per_hour")
    val milesPerHour: String? = null
)

data class MissDistance(

    @field:SerializedName("astronomical")
    val astronomical: String? = null,

    @field:SerializedName("kilometers")
    val kilometers: String? = null,

    @field:SerializedName("lunar")
    val lunar: String? = null,

    @field:SerializedName("miles")
    val miles: String? = null
)

data class EstimatedDiameter(

    @field:SerializedName("feet")
    @Embedded(prefix = "feet")
    val feet: Feet? = null,

    @field:SerializedName("kilometers")
    @Embedded(prefix = "kilometers")
    val kilometers: Kilometers? = null,

    @field:SerializedName("meters")
    @Embedded(prefix = "meters")
    val meters: Meters? = null,

    @field:SerializedName("miles")
    @Embedded(prefix = "miles")
    val miles: Miles? = null
)

data class Feet(

    @field:SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double? = null,

    @field:SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double? = null
)

data class Kilometers(

    @field:SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double? = null,

    @field:SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double? = null
)

data class Meters(

    @field:SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double? = null,

    @field:SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double? = null
)

data class Miles(

    @field:SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double? = null,

    @field:SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double? = null
)

data class Links(

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("previous")
    val previous: String? = null,

    @field:SerializedName("self")
    val self: String? = null
)

