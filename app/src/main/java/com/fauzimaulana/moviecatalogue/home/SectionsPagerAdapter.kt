package com.fauzimaulana.moviecatalogue.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fauzimaulana.moviecatalogue.movie.MovieFragment
import com.fauzimaulana.moviecatalogue.tv.TvFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        fragment = when (position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> Fragment()
        }
        return fragment
    }
}