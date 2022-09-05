package com.alfayedoficial.coreNetwork.utilities

object NetworkConstants {

    const val CONTENT_TYPE = "application/json"
    const val REQUEST_TIME = 4L
    const val REQUEST_TIME_Debug = 1L

    // type = 0 for development // type = 1 for production
    var BASE_URL: String = ""
    var BuildConfig_DEBUG: Boolean = true

}