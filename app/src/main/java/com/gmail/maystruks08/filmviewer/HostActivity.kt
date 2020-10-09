package com.gmail.maystruks08.filmviewer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gmail.maystruks08.domain.util.NetworkUtil
import com.gmail.maystruks08.filmviewer.core.base.BaseActivity
import com.gmail.maystruks08.filmviewer.core.ext.transaction
import com.gmail.maystruks08.filmviewer.ui.description.PagerMovieFragment
import com.gmail.maystruks08.filmviewer.ui.movielist.MovieListFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_host.*
import timber.log.Timber
import javax.inject.Inject

class HostActivity : BaseActivity(R.layout.activity_host), MovieListFragment.Listener {

    @Inject
    lateinit var networkUtil: NetworkUtil

    private var lastBackPressTime = 0L

    private var snackBar: Snackbar? = null

    private var toast: Toast? = null

    override fun inject() = App.hostComponent?.inject(this)

    override fun initViews() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        requestPermission()
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                val movieListFragment = MovieListFragment.getInstance()
                return@transaction add(R.id.fragment_container, movieListFragment)
            }
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

    override fun onBackPressed() {
        this.hideSoftKeyboard()
        this.navigateBack()
    }

    override fun onPause() {
        super.onPause()
        this.hideSoftKeyboard()
    }

    override fun onStop() {
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

    override fun onMovieSelected(movieIds: List<Int>, moviePosition: Int, view: View) {
        supportFragmentManager.transaction {
            val movieDescriptionFragment = PagerMovieFragment.getInstance(movieIds, moviePosition)
//            setReorderingAllowed(true)
//            addSharedElement(view, view.transitionName)
//            replace(R.id.fragment_container, movieDescriptionFragment, PagerMovieFragment::class.simpleName)
//            addToBackStack(null)
            replace(R.id.fragment_container, movieDescriptionFragment)
            addToBackStack(DESCRIPTION_TAG)
        }
    }


    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_PERMISSION
            )
        }
    }

    private fun navigateBack() {
        fragment_container?.let {
            when {
                supportFragmentManager.backStackEntryCount > 0 -> supportFragmentManager.popBackStack()
                lastBackPressTime < System.currentTimeMillis() - PRESS_TWICE_INTERVAL -> {
                    toast = Toast.makeText(applicationContext, getString(R.string.toast_exit_app_warning_text), Toast.LENGTH_SHORT)
                    toast?.show()
                    lastBackPressTime = System.currentTimeMillis()
                }
                else -> finish()
            }
        }
    }



    companion object {

        private const val PRESS_TWICE_INTERVAL = 2000

        private const val REQUEST_WRITE_PERMISSION = 786

        private const val DESCRIPTION_TAG = "DESCRIPTION"

        private const val MOVIE_LIST_TAG = "MOVIE_LIST"
    }
}
