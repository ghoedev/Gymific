package com.ghoe.gymfit.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ghoe.gymfit.AdManager
import com.ghoe.gymfit.ui.arms.ArmsFragment
import com.ghoe.gymfit.ui.cardio.CardioFragment
import com.ghoe.gymfit.ui.legs.LegsFragment
import timber.log.Timber

const val CARDIO_PAGE_INDEX = 0
const val LEGS_PAGE_INDEX = 1
const val ARMS_PAGE_INDEX = 2
var mInterstitialAd: com.google.android.gms.ads.InterstitialAd? = AdManager.showInterstitial()

class AppViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    init {
        Timber.i("AppViewPagerAdapter init")
    }

    override fun getItemCount() = tabFragmentsCreator.size

    override fun createFragment(position: Int): Fragment {

        if (mInterstitialAd != null)
            mInterstitialAd?.show()

        return tabFragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreator : Map<Int, ()->Fragment> = mapOf(
        CARDIO_PAGE_INDEX to {CardioFragment()},
        LEGS_PAGE_INDEX to {LegsFragment()},
        ARMS_PAGE_INDEX to{ArmsFragment()}
    )
}