package com.gmail.maystruks08.filmviewer.ui.description

import android.widget.Toast
import com.gmail.maystruks08.domain.repository.ImageLoader
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.ext.argument
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieDescriptionFragment : BaseFragment(R.layout.fragment_movie) {

    @Inject
    lateinit var viewModel: MovieDescriptionViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    var movieId: Int by argument()

    override fun inject() {
        App.movieDescriptionComponent?.inject(this)
    }

    override fun bindViewModel() {
        viewModel.movie.observe(viewLifecycleOwner, { infoView ->
            tvMovieName.text = infoView.name
            tvMovieDescription.text = infoView.description
            ivMoviePicture.setImageBitmap(infoView.imageBitmap)
        })

        viewModel.progressBar.observe(viewLifecycleOwner, { needToShowProgress ->
            if (needToShowProgress) showProgress() else hideProgress()
        })

        viewModel.toast.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initViews() {
        viewModel.initFragment(movieId)
    }

    override fun clearComponent() = App.clearMovieDescriptionComponent()

    companion object {

        fun getInstance(movieId: Int) = MovieDescriptionFragment().apply {
            this.movieId = movieId
        }
    }
}
