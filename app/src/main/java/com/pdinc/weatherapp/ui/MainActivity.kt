package com.pdinc.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pdinc.weatherapp.R
import com.pdinc.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val homeFragment = HomeFragment()
    val fragmentManager = supportFragmentManager
    var activeFragment: Fragment = homeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            fragmentManager.beginTransaction().apply {
                add(R.id.container, homeFragment, getString(R.string.home_dashboard))
            }.commit()
            initListeners()
        }

        private fun initListeners() {
            binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.homeFragment -> {
                        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                            .commit()
                        activeFragment = homeFragment
                        true
                    }

                    else -> false
                }
            }
        }
    }