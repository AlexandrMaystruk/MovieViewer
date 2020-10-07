package com.gmail.maystruks08.filmviewer.ui.description

import android.content.Context
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.base.FragmentToolbar
import com.gmail.maystruks08.filmviewer.core.ext.argument
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import kotlinx.android.synthetic.main.fragment_movie_description.*

class MovieDescriptionFragment : BaseFragment(R.layout.fragment_movie_description) {

    private lateinit var viewModel: MovieDescriptionViewModel

    private var callback: Listener? = null

    var movieId: Int by argument()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as? Listener
    }

    override fun injectDependencies() {
        App.movieDescriptionComponent?.inject(this)
        viewModel = injectViewModel(viewModeFactory)
    }

    override fun initToolbar() = FragmentToolbar.Builder()
        .withId(R.id.toolbar)
        .withTitle(R.string.app_name)
        .withNavigationIcon(R.drawable.ic_arrow_back) { callback?.onBackClicked() }
        .build()

    override fun bindViewModel() {
        viewModel.movie.observe(viewLifecycleOwner, {
            tvMovieName.text = it.name
            tvMovieDescription.text = it.description
        })

        viewModel.toast.observe(viewLifecycleOwner, {
            //TODO show on UI
        })
    }

    override fun initViews() {
       viewModel.initFragment(movieId)
    }

    fun refreshUI(movieId: Int){
        viewModel.initFragment(movieId)
    }

    override fun clearInjectedComponent() = App.clearMovieDescriptionComponent()


    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    interface Listener {

        fun onBackClicked()

    }

    companion object {

        fun getInstance(position: Int) = MovieDescriptionFragment().apply { this.movieId = position }

    }

}
