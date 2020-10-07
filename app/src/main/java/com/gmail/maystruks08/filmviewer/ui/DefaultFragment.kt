package com.gmail.maystruks08.filmviewer.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseFragment
import com.gmail.maystruks08.filmviewer.core.base.FragmentToolbar
import com.gmail.maystruks08.filmviewer.core.ext.argument
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import kotlinx.android.synthetic.main.fragment_default.*

class DefaultFragment : BaseFragment(R.layout.fragment_default) {

    lateinit var viewModel: DefaultViewModel

    private lateinit var resultAdapter: DefaultAdapter

    var parameter: Int by argument()

    override fun injectDependencies() {
        App.defaultComponent?.inject(this)
        viewModel = injectViewModel(viewModeFactory)
    }

    override fun initToolbar() = FragmentToolbar.Builder()
        .withId(R.id.toolbar)
        .withNavigationIcon(R.drawable.ic_arrow_back) {
            //TODO handle click on navigation back
        }
        .withTitle(R.string.app_name)
        .build()

    override fun bindViewModel() {

        viewModel.defaultViews.observe(viewLifecycleOwner, {
            //TODO show on UI
        })
    }

    override fun initViews() {
        resultAdapter = DefaultAdapter {
            //TODO handle click
        }
        defaultRecyclerView.apply {
            layoutManager = LinearLayoutManager(defaultRecyclerView.context)
            adapter = resultAdapter
        }
    }


    override fun onDestroyView() {
        defaultRecyclerView.adapter = null
        super.onDestroyView()
    }

    override fun clearInjectedComponent() = App.clearDefaultComponent()


    companion object {

        fun getInstance(parameter: Int) =
            DefaultFragment().apply { this.parameter = parameter }
    }

}
