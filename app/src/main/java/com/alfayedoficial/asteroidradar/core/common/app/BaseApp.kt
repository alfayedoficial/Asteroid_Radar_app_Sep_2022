package com.alfayedoficial.asteroidradar.core.common.app

import android.os.Build
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.work.*
import com.alfayedoficial.asteroidradar.core.workManager.WorkManagerHelper.Companion.WORK_NAME
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidApp
class BaseApp : MultiDexApplication() {

    @Inject
    @Named("urlDomainOfDeployment")
    lateinit var deployment: String

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        NetworkConstants.BASE_URL = deployment

        initWorkManager()
    }

    private fun initWorkManager() {
        CoroutineScope(Dispatchers.Default).launch {
            val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true).setRequiresBatteryNotLow(true).apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()

            val repeat = PeriodicWorkRequestBuilder<Worker>(1, TimeUnit.DAYS)
                .setConstraints(constraint).build()

            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(WORK_NAME ,  ExistingPeriodicWorkPolicy.KEEP, repeat)
        }
    }


}