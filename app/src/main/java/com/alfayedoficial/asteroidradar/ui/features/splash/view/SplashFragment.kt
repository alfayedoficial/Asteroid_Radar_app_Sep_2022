package com.alfayedoficial.asteroidradar.ui.features.splash.view

import android.os.Handler
import android.os.Looper
import com.alfayedoficial.kotlinutils.kuRunDelayed
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.asteroidradar.core.common.fragment.BaseFragment
import com.alfayedoficial.asteroidradar.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    val handler = Handler(Looper.myLooper()!!)
    override val layoutResourceLayout: Int
        get() = R.layout.fragment_splash

    override fun onFragmentCreated(dataBinder: FragmentSplashBinding) {
        dataBinder.apply {
            fragment = this@SplashFragment
            lifecycleOwner = this@SplashFragment
        }
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed({
            navController.navigate(R.id.action_splashFragment_to_homeFragment)
        }, 5900)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

    override fun setUpViewModelStateObservers() { println("Splash Fragment") }

}