package com.nicktz.minimercari

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GeneralPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList = mutableListOf<Fragment>()

    private val fragmentTitleList = mutableListOf<String>()

    override fun getCount(): Int {
        return if (fragmentList.isEmpty()) 0 else fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFrag(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (fragmentTitleList.isEmpty()) "" else fragmentTitleList[position]
    }
}