package com.codingblocks.weatherapp.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.pdinc.weatherapp.R
import com.pdinc.weatherapp.WeatherApplication
import com.pdinc.weatherapp.utils.GPS_REQUEST_CHECK_SETTINGS
import com.pdinc.weatherapp.utils.GpsUtil
import com.pdinc.weatherapp.utils.SharedPreferenceHelper
import com.pdinc.weatherapp.utils.observeOnce

class HomeFragment : Fragment() {
lateinit var binding:HomeFragment
    private lateinit var homeView: View
    private var isGPSEnabled = false
    private lateinit var prefs: SharedPreferenceHelper

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModel.HomeFragmentViewModelFactory(
            (requireContext().applicationContext as WeatherApplication).weatherRepository,
            requireActivity().application
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPreferenceHelper.getInstance(requireContext())
        GpsUtil(requireContext()).turnGPSOn(object : GpsUtil.OnGpsListener {
            override fun gpsStatus(isGPSEnabled: Boolean) {
                this@HomeFragment.isGPSEnabled = isGPSEnabled
            }
        })
    }
    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    private fun invokeLocationAction() {
        when {
            allPermissionsGranted() -> {
                viewModel.getLocationLiveData().observeOnce(
                    viewLifecycleOwner,
                    Observer { location ->
                        if (location != null) {
                            viewModel.getWeather(location)
                        }
                    }
                )
            }

            shouldShowRequestPermissionRationale() -> {
                AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission")
                    .setMessage("This application requires access to your location to function!")
                    .setNegativeButton(
                        "No"
                    ) { _, _ -> requireActivity().finish() }
                    .setPositiveButton(
                        "Ask me"
                    ) { _, _ ->
                        requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
                    }
                    .show()
            }

            !isGPSEnabled -> {
                Snackbar.make(
                    homeView.rootView,
                    "GPS is required for this application to function!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            else -> {
                requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeView =  inflater.inflate(R.layout.home_fragment, container, false)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    GPS_REQUEST_CHECK_SETTINGS -> {
                        isGPSEnabled = true
                        invokeLocationAction()
                    }
                }
            }

            Activity.RESULT_CANCELED -> {
                when (requestCode) {
                    GPS_REQUEST_CHECK_SETTINGS -> {
                        Snackbar.make(
                            homeView.rootView,
                            "Enable your GPS and restart!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun shouldShowRequestPermissionRationale() = REQUIRED_PERMISSIONS.all {
        shouldShowRequestPermissionRationale(it)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            invokeLocationAction()
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        private const val LOCATION_REQUEST_CODE = 123
    }


}