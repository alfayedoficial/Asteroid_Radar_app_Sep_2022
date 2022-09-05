package com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.alfayedoficial.coreModel.domain.repo.DBMainRepository
import com.google.gson.annotations.SerializedName

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :Entity of AsteroidImage
 * @see DBMainRepository
 * Date 29/8/2022 - 12:39 PM
 */
@Entity(tableName = "image_table" , indices = [Index(value = ["imageTitle" , "imageUrl"], unique = true)])
data class AsteroidImagesResponse(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "imageLocal")     var idLocal: Int? = null,
	@field:SerializedName("date")
	@ColumnInfo(name = "imageDate")      val date: String? = null,
	@field:SerializedName("title")
	@ColumnInfo(name = "imageTitle")     val title: String? = null,
	@field:SerializedName("url")
	@ColumnInfo(name = "imageUrl")       val url: String? = null
)
