package com.fauzimaulana.moviecatalogue.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fauzimaulana.moviecatalogue.favorite.movie.FavoriteMovieFragment
import com.fauzimaulana.moviecatalogue.favorite.tv.FavoriteTvFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        fragment = when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> Fragment()
        }
        return fragment
    }
}