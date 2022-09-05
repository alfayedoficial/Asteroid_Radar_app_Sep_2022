package com.alfayedoficial.asteroidradar.ui.features.home.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.asteroidradar.callback.OnRecyclerViewIsSelected
import com.alfayedoficial.asteroidradar.core.common.fragment.BaseFragment
import com.alfayedoficial.asteroidradar.databinding.FragmentHomeBinding
import com.alfayedoficial.asteroidradar.ui.features.home.adapter.AsteroidRvAdapter
import com.alfayedoficial.asteroidradar.ui.features.home.viewModel.HomeViewModel
import com.alfayedoficial.asteroidradar.utilities.TemplateEnums
import com.alfayedoficial.asteroidradar.utilities.setBaseActivityFragmentsToolbar
import com.alfayedoficial.coreModel.local.AsteroidEarth
import com.alfayedoficial.kotlinutils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() , OnRecyclerViewIsSelected {

    private val mViewModel by activityViewModels<HomeViewModel>()
    private val adapterAsteroidRv : AsteroidRvAdapter by lazy { AsteroidRvAdapter() }

    override val layoutResourceLayout: Int
        get() = R.layout.fragment_home

    override fun onFragmentCreated(dataBinder: FragmentHomeBinding) {
        dataBinder.apply {
            fragment = this@HomeFragment
            lifecycleOwner = this@HomeFragment
            viewModel = mViewModel

            homeToolbar.apply { setBaseActivityFragmentsToolbar(kuRes.getString(R.string.app_name), toolbar, tvNameToolbar) }

           setMenu()

            swipeToRefresh.setOnRefreshListener {
                mViewModel.initData()
            }
        }
    }

    private fun setMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.header_menu_dashboard, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId ){
                    R.id.show_week_menu -> MainScope().launch { mViewModel.filterLocalData(TemplateEnums.Filter.WEEK.type) }
                    R.id.show_today_menu -> MainScope().launch { mViewModel.filterLocalData(TemplateEnums.Filter.TODAY.type) }
                    R.id.show_saved_menu -> MainScope().launch { mViewModel.filterLocalData(TemplateEnums.Filter.SAVED.type) }
                }
                return true
            }

        }  , viewLifecycleOwner, Lifecycle.State.RESUMED)
    }



    override fun setUpViewModelStateObservers() {
        mViewModel.progressLoadingLiveData.observe(viewLifecycleOwner){uiLoading(it) }
        mViewModel.asteroidList.observe(viewLifecycleOwner){uiAsteroidList(it) }
    }

    private fun uiAsteroidList(it: List<AsteroidEarth>?) {
        dataBinder.apply {
            rvAsteroid.recyclerviewBase.apply {
                kuInitLinearLayoutManager(RecyclerView.VERTICAL , adapterAsteroidRv)
                adapterAsteroidRv.initSelector(this@HomeFragment)
                adapterAsteroidRv.setDataList(it?: arrayListOf())
            }
        }
    }

    private fun uiLoading(it: Boolean?) {
        dataBinder.apply {
            swipeToRefresh.isRefreshing = false
            if (it == null || it == true) {
                shimmerViewContainer.kuShow()
                shimmerViewContainer.startShimmer()
                swipeToRefresh.kuHide()
            }else{
                shimmerViewContainer.kuHide()
                shimmerViewContainer.stopShimmer()
                swipeToRefresh.kuShow()
            }
        }
    }

    override fun onSelected(id: Int?, viewId: Int?, viewName: String?, data: Any?) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data as AsteroidEarth))
    }


}