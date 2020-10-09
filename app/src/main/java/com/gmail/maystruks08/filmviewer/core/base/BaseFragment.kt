package com.gmail.maystruks08.filmviewer.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.DaggerViewModelFactory
import com.gmail.maystruks08.filmviewer.core.ext.hide
import com.gmail.maystruks08.filmviewer.core.ext.show
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes private val layout: Int? = null) : Fragment() {

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layout?.let { inflater.inflate(layout, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        initViews()
    }

    protected abstract fun inject(): Unit?

    protected abstract fun bindViewModel(): Unit?

    protected open fun initViews() {}

    protected abstract fun clearComponent(): Unit?

    protected fun showProgress() {
        view?.findViewById<ProgressBar>(R.id.progressSpinner)?.show()
    }

    protected fun hideProgress() {
        view?.findViewById<ProgressBar>(R.id.progressSpinner)?.hide()
    }

    override fun onDetach() {
        clearComponent()
        super.onDetach()
    }
}