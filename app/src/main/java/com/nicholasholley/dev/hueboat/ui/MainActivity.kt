package com.nicholasholley.dev.hueboat.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.ui.discovery.DiscoveryKey
import com.zhuinden.simplestack.BackstackDelegate
import com.zhuinden.simplestack.HistoryBuilder
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, StateChanger {
    val navigationController: NavigationController by lazy {
        NavigationController(supportFragmentManager)
    }
    val backstackDelegate: BackstackDelegate = BackstackDelegate(this)
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
//    val vm: vm by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(
//                vm::class.java
//        )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBackstack(savedInstanceState)
    }

    //HasSupportFragmentInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }


    /***
     * Handle global navigation actions
     */
    fun setupBackstack(savedInstanceState: Bundle?) {
        backstackDelegate.onCreate(
                savedInstanceState,
                lastCustomNonConfigurationInstance,
                arrayListOf(DiscoveryKey)
        )
        backstackDelegate.registerForLifecycleCallbacks(this)
        backstackDelegate.setStateChanger(this)
    }

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        if (stateChange.topNewState<Any>() == stateChange.topPreviousState<Any>()) {
            completionCallback.stateChangeComplete()
            return
        }
        navigationController.handleStateChange(stateChange)
        completionCallback.stateChangeComplete()
    }

    private fun replaceHistory(rootKey: Any) {
        backstackDelegate.backstack.setHistory(HistoryBuilder.single(rootKey), StateChange.REPLACE)
    }

    fun navigateTo(key: Any) {
        backstackDelegate.backstack.goTo(key)
    }

    override fun onBackPressed() {
        if (!backstackDelegate.onBackPressed()) super.onBackPressed()
    }

    override fun onRetainCustomNonConfigurationInstance() =
            backstackDelegate.onRetainCustomNonConfigurationInstance()

    override fun getSystemService(name: String): Any? {
        return when {
            name == MainActivity.TAG -> this
            else -> super.getSystemService(name)
        }
    }

    companion object {
        private val TAG = this::class.java.name

        @SuppressLint("WrongConstant")
        operator fun get(context: Context): MainActivity {
            return context.getSystemService(TAG) as MainActivity
        }
    }
}
