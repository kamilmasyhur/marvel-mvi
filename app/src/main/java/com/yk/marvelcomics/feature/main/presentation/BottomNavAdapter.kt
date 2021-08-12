package com.yk.marvelcomics.feature.main.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomNavAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragments: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(index = position)
    }

    fun addFragments(fragments: Map<Int, Fragment>) {
        fragments.forEach {
            this.fragments.add(it.value)
        }
    }
}
