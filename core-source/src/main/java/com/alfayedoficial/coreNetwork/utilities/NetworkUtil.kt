package com.alfayedoficial.coreNetwork.utilities

import com.alfayedoficial.coreNetwork.utilities.NetworkConstants.BASE_URL

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Utils Network
 * Date 6/12/2021 - 3:08 PM
 */
object NetworkUtil {

  const val PATH = "PATH"

  enum class NetworkLinks(val type: String) {

    /**-----------------------   GET   ---------------------*/
    GetAsteroidData("/neo/rest/v1/feed"),
    GetAsteroidImages("/planetary/apod"),


    /**-----------------------   POST   ---------------------*/

  }

  fun getApiLink(endPoint: String, path: String? = null) = BASE_URL.plus(endPoint).replace(PATH, path ?: "")
}