package com.gmail.maystruks08.filmviewer.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(private val layout: Int? = null) : Fragment() {

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
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

    override fun onStart() {
        (activity as? BaseActivity)?.configToolbar(initToolbar())
        super.onStart()
    }

    protected abstract fun injectDependencies(): Unit?

    protected abstract fun initToolbar(): FragmentToolbar

    protected abstract fun bindViewModel(): Unit?

    protected open fun initViews() {}

    protected abstract fun clearInjectedComponent(): Unit?

    override fun onDetach() {
        clearInjectedComponent()
        super.onDetach()
    }
}