package com.gmail.maystruks08.filmviewer.ui.movielist

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.maystruks08.domain.repository.FileHelper
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.base.FragmentToolbar
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    @Inject
    lateinit var fileHelper: FileHelper

    private lateinit var viewModel: MovieListViewModel

    private lateinit var movieAdapter: MovieAdapter

    private var callback: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as? Listener
    }

    override fun injectDependencies() {
        App.movieListComponent?.inject(this)
        viewModel = injectViewModel(viewModeFactory)
    }

    override fun initToolbar() = FragmentToolbar.Builder()
        .withId(R.id.toolbar)
        .withTitle(R.string.app_name)
        .build()

    override fun bindViewModel() {
        viewModel.movieViews.observe(viewLifecycleOwner, { movies ->
            movieAdapter.viewList = movies
        })

        viewModel.progressBar.observe(viewLifecycleOwner, { needToShowProgress ->

        })

        viewModel.toast.observe(viewLifecycleOwner, { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initViews() {
        movieAdapter = MovieAdapter(fileHelper, viewLifecycleOwner, MovieDiffUtilCallback(), { movieId ->
            callback?.onMovieSelected(movieId)
        })
        movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        movieRecyclerView.adapter = null
        super.onDestroyView()
    }

    override fun clearInjectedComponent() = App.clearMovieListComponent()

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    interface Listener {

        fun onMovieSelected(movieId: Int)

    }

    companion object {

        fun getInstance() = MovieListFragment()

    }

}
