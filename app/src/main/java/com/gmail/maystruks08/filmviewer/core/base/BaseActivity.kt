package com.gmail.maystruks08.filmviewer.core.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.DaggerViewModelFactory
import kotlinx.android.synthetic.main.activity_host.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {

    var toolbarManager: ToolbarManager? = null

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        appBarLayout.isLiftOnScroll

        toolbarManager = ToolbarManager(initToolbar(), toolbar).apply { prepareToolbar() }
        initViews()
    }

    override fun onBackPressed() {
        this.hideSoftKeyboard()
    }

    protected abstract fun injectDependencies(): Unit?

    protected abstract fun initToolbar(): FragmentToolbar

    protected abstract fun bindViewModel(): Unit?

    protected open fun initViews() {}

    fun configToolbar(fragmentToolbar: FragmentToolbar){
        toolbarManager = ToolbarManager(fragmentToolbar, toolbar).apply { prepareToolbar() }
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
