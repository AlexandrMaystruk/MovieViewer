package com.gmail.maystruks08.filmviewer.ui.description

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlin.properties.Delegates

class PagerAdapter constructor(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var movieIds: List<Int> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        val movieId = movieIds[position]
        return MovieDescriptionFragment.getInstance(movieId)
    }

    override fun getCount(): Int = movieIds.size

    fun updateData(list: List<Int>) {
        movieIds = list
    }

}