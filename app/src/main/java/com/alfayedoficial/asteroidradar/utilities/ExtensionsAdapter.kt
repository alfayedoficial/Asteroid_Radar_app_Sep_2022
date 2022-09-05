package com.alfayedoficial.asteroidradar.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.kotlinutils.kuRes
import com.squareup.picasso.Picasso


/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Adapter Extensions
 * Date 1/1/2021 - 4:59 PM
 */

@BindingAdapter("app:bindString")
fun TextView.setBindString(txtString: String?) {
    text = txtString ?: ""
}

@BindingAdapter("app:bindImgUrlWithProgress" , "app:bindProgressBar")
fun ImageView.setBindImageWithProgressBar(url: String?, progress: View) {
   if (url != null && url.isNotEmpty()) {
       Picasso.get()
           .load(url)
           .placeholder(R.drawable.place_holder)
           .into(this, object : com.squareup.picasso.Callback {
               override fun onSuccess() {
                   progress.visibility = View.GONE
               }

               override fun onError(e: Exception?) {
                   progress.visibility = View.GONE
                   this@setBindImageWithProgressBar.setImageResource(R.drawable.place_holder)
               }
           })
        } else {
            this.setImageResource(R.drawable.place_holder)
           // progress.visibility = View.GONE
        }
   }

fun TextView.setFormatString(txtString: String? ,res: Int) {
    text =  String.format(kuRes.getString(res) , txtString?:"" )
}

@BindingAdapter("app:bindIcon")
fun ImageView.setBindAsteroidStatusImage(isHazardous: Boolean) {
    val img =if (isHazardous) {
        R.drawable.ic_status_potentially_hazardous
    } else {
        R.drawable.ic_status_normal
    }
    setImageResource(img)
}

@BindingAdapter("app:bindAstronomicalUnitText")
fun TextView.bindTextViewToAstronomicalUnit( number: Double) {
    text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("app:bindAsteroidStatusImage")
fun ImageView.bindDetailsStatusImage(isHazardous: Boolean) {
    val img = if (isHazardous) {
        R.drawable.asteroid_hazardous
    } else {
        R.drawable.asteroid_safe
    }
    setImageResource(img)
}

@BindingAdapter("app:bindKmUnitText")
fun TextView.bindTextViewToKmUnit( number: Double) {
    text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("app:bindVelocityText")
fun TextView.bindTextViewToDisplayVelocity(number: Double) {
   text = String.format(context.getString(R.string.km_s_unit_format), number)
}




