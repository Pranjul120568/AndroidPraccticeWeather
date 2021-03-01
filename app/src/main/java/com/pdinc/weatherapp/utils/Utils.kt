package com.pdinc.weatherapp.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.String.format
import java.text.DecimalFormat
import java.util.logging.Level.parse

fun convertKelvinToCelsius(number: Number): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        parse(format(number.toDouble().minus(273))).toDouble()
    }
}

/**
 * This function helps to toggle the visibility of a [View]. If the condition
 * is met, the [View] is made visible else it is hidden.
 */
inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    visibility = if (condition(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * This functions helps in transforming a [MutableLiveData] of type [T]
 * to a [LiveData] of type [T]
 */
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

/**
 * This function helps to observe a [LiveData] once
 */
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(
            lifecycleOwner,
            object : Observer<T> {
                override fun onChanged(t: T?) {
                    observer.onChanged(t)
                    removeObserver(this)
                }
            }
    )
}