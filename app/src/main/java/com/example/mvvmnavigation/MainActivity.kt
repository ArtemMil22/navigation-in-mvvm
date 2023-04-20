package com.example.mvvmnavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnavigation.navigator.MainNavigator
import com.example.mvvmnavigation.screens.base.BaseFragment
import com.example.mvvmnavigation.screens.hello.HelloFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val navigator by viewModels<MainNavigator> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.launchFragment(
                this, HelloFragment.Screen(), addToBackStack = false
            ) }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks,false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        navigator.whenActivityActive.mainActivity = this
    }

    override fun onPause() {
        super.onPause()
        navigator.whenActivityActive.mainActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager
            .unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
    }

    //прослушиваем фрагменты
    private val fragmentCallbacks =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                val result = navigator.result.value?.getValue() ?: return
                Log.d("MyLogi","Callback $result")
                if(f is BaseFragment){
                    f.viewModel.onResult(result)
                }

                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                } else {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            }
        }
}