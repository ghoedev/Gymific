package com.ghoe.gymfit.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ghoe.gymfit.R
import com.ghoe.gymfit.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ghoe.gymfit.AdManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    var mInterstitialAd: com.google.android.gms.ads.InterstitialAd? = AdManager.showInterstitial()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mInterstitialAd != null)
            mInterstitialAd?.show()

        Timber.i("ViewPagerFragment init")
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupViewPagerWithTabs()
        return binding.root
    }

    private fun setupViewPagerWithTabs() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.pager

        viewPager.adapter = AppViewPagerAdapter(this)

        // Set the title for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
       return when(position){
           CARDIO_PAGE_INDEX -> getString(R.string.title_cardio)
           LEGS_PAGE_INDEX -> getString(R.string.title_legs)
           ARMS_PAGE_INDEX -> getString(R.string.title_arms)
           else -> null
       }
    }
}