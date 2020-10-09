package com.gmail.maystruks08.filmviewer.ui.description

import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.ext.argument
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.item_movie.view.*

class PagerMovieFragment : BaseFragment(R.layout.fragment_pager) {

    private var pagerAdapter: PagerAdapter? = null

    var movieIds: List<Int> by argument()

    var selectedMoviePosition: Int by argument()

    override fun inject() = Unit
    override fun bindViewModel() = Unit
    override fun clearComponent() = Unit

    override fun initViews() {
        pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        pagerAdapter?.updateData(movieIds.toMutableList())
        viewPager.currentItem = selectedMoviePosition


        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(names: List<String?>, sharedElements: MutableMap<String?, View?>) {
                    val currentFragment = viewPager.adapter?.instantiateItem(viewPager, selectedMoviePosition) as Fragment
                    val view: View = currentFragment.view ?: return
                    sharedElements[names[0]] = view.findViewById(R.id.ivMoviePicture)
                }
            })
    }

    override fun onDestroyView() {
        viewPager.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance(movieIds: List<Int>, selectedMoviePosition: Int = 0) =
            PagerMovieFragment().apply {
                this.movieIds = movieIds
                this.selectedMoviePosition = selectedMoviePosition
            }
    }
}
