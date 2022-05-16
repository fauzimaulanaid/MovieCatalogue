package com.fauzimaulana.moviecatalogue.favorite

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.fauzimaulana.moviecatalogue.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        loadKoinModules(favoriteModule)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        activityFavoriteBinding.viewPagerFavorite.adapter = sectionsPagerAdapter
        TabLayoutMediator(activityFavoriteBinding.tabsFavorite, activityFavoriteBinding.viewPagerFavorite) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.title = resources.getString(R.string.favorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv
        )
    }
}