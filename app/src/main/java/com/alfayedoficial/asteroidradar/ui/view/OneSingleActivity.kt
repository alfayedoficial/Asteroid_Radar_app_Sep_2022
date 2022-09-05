package com.alfayedoficial.asteroidradar.ui.view

import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.asteroidradar.R.*
import com.alfayedoficial.asteroidradar.core.common.activity.BaseActivity
import com.alfayedoficial.asteroidradar.databinding.ActivityOneSingleBinding
import com.alfayedoficial.asteroidradar.ui.features.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneSingleActivity : BaseActivity<ActivityOneSingleBinding>() {

    private val mViewModel by viewModels <HomeViewModel>()

    override val layoutResourceId: Int
        get() = R.layout.activity_one_single

    override fun onActivityCreated(dataBinder: ActivityOneSingleBinding) {
        dataBinder.apply {
            activity = this@OneSingleActivity
            lifecycleOwner = this@OneSingleActivity

            val navHostFragment = supportFragmentManager.findFragmentById(id.navLaunchGraph) as NavHostFragment
            navController = navHostFragment.navController

            appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf(
                    // set all your top level destinations in here
                    id.homeFragment,
                )
            )
        }
    }
}