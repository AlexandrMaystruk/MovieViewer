package com.gmail.maystruks08.filmviewer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gmail.maystruks08.domain.util.NetworkUtil
import com.gmail.maystruks08.filmviewer.core.base.BaseActivity
import com.gmail.maystruks08.filmviewer.core.base.FragmentToolbar
import com.gmail.maystruks08.filmviewer.core.ext.getFragment
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import com.gmail.maystruks08.filmviewer.core.ext.transaction
import com.gmail.maystruks08.filmviewer.ui.description.MovieDescriptionFragment
import com.gmail.maystruks08.filmviewer.ui.movielist.MovieListFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_host.*
import timber.log.Timber
import javax.inject.Inject

const val PRESS_TWICE_INTERVAL = 2000

class HostActivity : BaseActivity(R.layout.activity_host), MovieListFragment.Listener,
    MovieDescriptionFragment.Listener {

    @Inject
    lateinit var networkUtil: NetworkUtil

    private lateinit var viewModel: HostViewModel

    private var lastBackPressTime = 0L

    private var alertDialog: AlertDialog? = null

    private var snackBar: Snackbar? = null

    private var toast: Toast? = null

    override fun injectDependencies() {
        App.hostComponent?.inject(this)
        viewModel = injectViewModel(viewModeFactory)
    }

    override fun initToolbar(): FragmentToolbar = FragmentToolbar.Builder()
        .withId(R.id.toolbar)
        .withTitle(R.string.app_name)
        .build()

    override fun bindViewModel() {

    }

    override fun initViews() {
        fragment_container?.let {
            supportFragmentManager.transaction {
                val movieListFragment = MovieListFragment.getInstance()
                return@transaction add(R.id.fragment_container, movieListFragment)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Timber.e("PERMISSIONS onRequestPermissionsResult true")
        }
    }

    override fun onMovieSelected(movieId: Int) {
        val fragment = getFragment<MovieDescriptionFragment>(DESCRIPTION_TAG)
        if (fragment_container != null && fragment == null) {
            supportFragmentManager.transaction {
                val movieDescriptionFragment = MovieDescriptionFragment.getInstance(movieId)
                return@transaction replace(R.id.fragment_container, movieDescriptionFragment)
            }
        } else {
            fragment?.refreshUI(movieId)
        }
    }

    override fun onBackClicked() {
        onBackPressed()
    }

    override fun onBackPressed() {
        this.hideSoftKeyboard()
        this.navigateBack()
        fragment_container?.let {
            supportFragmentManager.transaction {
                val movieListFragment = MovieListFragment.getInstance()
                return@transaction replace(R.id.fragment_container, movieListFragment)
            }
        }
    }

    private fun navigateBack() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> viewModel.onExitClicked()
            lastBackPressTime < System.currentTimeMillis() - PRESS_TWICE_INTERVAL -> {
                toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit_app_warning_text),
                    Toast.LENGTH_SHORT
                )
                toast?.show()
                lastBackPressTime = System.currentTimeMillis()
            }
            else -> viewModel.onExitClicked()
        }
    }

    override fun onPause() {
        super.onPause()
        alertDialog?.dismiss()
        this.hideSoftKeyboard()
    }

    override fun onStop() {
        toast?.cancel()
        networkUtil.unsubscribe(this.javaClass.simpleName)
        super.onStop()
    }

    override fun onDestroy() {
        toast?.cancel()
        toast = null
        snackBar?.dismiss()
        snackBar = null
        App.clearHostComponent()
        super.onDestroy()
    }

    companion object {

        private const val REQUEST_WRITE_PERMISSION = 786


        private const val DESCRIPTION_TAG = "DESCRIPTION"
    }
}
