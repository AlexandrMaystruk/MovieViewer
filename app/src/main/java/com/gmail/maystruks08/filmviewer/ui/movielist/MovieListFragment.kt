package com.gmail.maystruks08.filmviewer.ui.movielist

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.maystruks08.domain.repository.ImageLoader
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var movieAnimator: MovieAnimator

    private lateinit var viewModel: MovieListViewModel

    private lateinit var movieAdapter: MovieAdapter

    private var callback: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as? Listener
    }

    override fun inject() {
        App.movieListComponent?.inject(this)
        viewModel = injectViewModel(viewModeFactory)
    }

    override fun bindViewModel() {
        viewModel.movieViews.observe(viewLifecycleOwner, { movies ->
            movieAdapter.viewList = movies
        })

        viewModel.progressBar.observe(viewLifecycleOwner, { needToShowProgress ->
            if (needToShowProgress) showProgress() else hideProgress()
        })

        viewModel.toast.observe(viewLifecycleOwner, { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initViews() {
        movieAnimator.setUpReturnTransition(this)
        movieAdapter = MovieAdapter(imageLoader, viewLifecycleOwner, MovieDiffUtilCallback(), { moviePosition, view ->
            callback?.onMovieSelected(movieAdapter.viewList.map { it.id }, moviePosition, view)
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

    override fun clearComponent() = App.clearMovieListComponent()

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    interface Listener {

        fun onMovieSelected(movieIds: List<Int>, moviePosition: Int, view: View)

    }

    companion object {

        fun getInstance() = MovieListFragment()

    }

}
