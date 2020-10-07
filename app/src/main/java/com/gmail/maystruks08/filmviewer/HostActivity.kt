package com.gmail.maystruks08.filmviewer

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gmail.maystruks08.domain.util.NetworkUtil
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.DaggerViewModelFactory
import com.gmail.maystruks08.filmviewer.core.ext.injectViewModel
import com.gmail.maystruks08.filmviewer.core.navigation.AppNavigator
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_host.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

const val PRESS_TWICE_INTERVAL = 2000

class HostActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var networkUtil: NetworkUtil

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var viewModel: HostViewModel

    private var lastBackPressTime = 0L

    private var alertDialog: AlertDialog? = null

    private var snackBar: Snackbar? = null

    private var toast: Toast? = null

    private val navigator: Navigator =
        object : AppNavigator(this, supportFragmentManager, R.id.nav_host_container) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        App.hostComponent?.inject(this)

        viewModel = injectViewModel(viewModeFactory)

        networkUtil.subscribeToConnectionChange(this.javaClass.simpleName) { isConnected ->
            if (!isConnected) {
                snackBar = Snackbar.make(
                    nav_host_container,
                    "Snack bar text",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackBar?.view?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    ?.apply {
                        isSingleLine = false
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                snackBar?.show()
            } else {
                snackBar?.dismiss()
            }
        }
    }

    override fun onBackPressed() {
        this.hideSoftKeyboard()
        this.navigateBack()
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

    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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
}
