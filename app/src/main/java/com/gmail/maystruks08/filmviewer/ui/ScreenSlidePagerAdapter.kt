package com.gmail.maystruks08.filmviewer.ui

import android.os.Parcelable
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import timber.log.Timber

class ScreenSlidePagerAdapter(
    private val titles: Array<String>, fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val registeredFragments: SparseArray<DefaultFragment> = SparseArray()

    override fun getCount(): Int = titles.size

    override fun getItem(position: Int): Fragment = DefaultFragment.getInstance(position)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as DefaultFragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? = titles.getOrNull(position)


    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        try {
            super.restoreState(state, loader)
        } catch (e: Exception) {
            Timber.d("Error Restore State of Fragment")
        }
    }

    fun getCurrentVisibleFragment(position: Int): DefaultFragment? = registeredFragments[position]
}