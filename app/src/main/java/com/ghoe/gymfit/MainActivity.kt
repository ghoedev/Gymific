package com.ghoe.gymfit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ghoe.gymfit.databinding.ActivityMainBinding
import com.ghoe.gymfit.ui.viewpager.mInterstitialAd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var appOpenManager: AppOpenManager? = null
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        MobileAds.initialize(
            this
        ) { }
        appOpenManager = AppOpenManager(this)
        val adRequest = AdRequest.Builder().build()



        ad_view2.loadAd(adRequest)


        var adManager = AdManager(this, getString(R.string.ad_unit_id))
        adManager.createAd()
        if (mInterstitialAd != null)
            mInterstitialAd?.show()

        navController = findNavController(R.id.nav_host_fragment)
        setupBottomNavMenu(navController)
        ad_view2.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }


        private fun setupBottomNavMenu(navController: NavController) {
        bottomNav = binding.navView

        bottomNav.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener {
                if (AdManager.interstitialAd != null) {
                    AdManager.showInterstitial()
                }
            // Do nothing to ignore the reselection
            }
        }
    }

}