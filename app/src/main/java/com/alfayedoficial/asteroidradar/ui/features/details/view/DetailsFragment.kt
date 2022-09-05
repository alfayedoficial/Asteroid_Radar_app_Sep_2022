package com.alfayedoficial.asteroidradar.ui.features.details.view

import androidx.appcompat.app.AlertDialog
import com.alfayedoficial.kotlinutils.kuRes
import com.alfayedoficial.kotlinutils.kuTakeFocus
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.asteroidradar.core.common.fragment.BaseFragment
import com.alfayedoficial.asteroidradar.databinding.FragmentDetailsBinding
import com.alfayedoficial.asteroidradar.utilities.setBaseActivityFragmentsToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val layoutResourceLayout: Int
        get() = R.layout.fragment_details

    override fun onFragmentCreated(dataBinder: FragmentDetailsBinding) {
        dataBinder.apply {
            fragment = this@DetailsFragment
            lifecycleOwner = this@DetailsFragment


            arguments?.let {
                val selectedAsteroid =  DetailsFragmentArgs.fromBundle(it).selectedAsteroid
                model = selectedAsteroid
                detailsToolbar.apply { setBaseActivityFragmentsToolbar(selectedAsteroid?.name?:"", toolbar, tvNameToolbar) }

            }

        }
    }

    override fun setUpViewModelStateObservers() { println("Details Fragment") }

    fun takeFocus() = kuTakeFocus(dataBinder.lyParent)

    fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(activity!!)
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }

}