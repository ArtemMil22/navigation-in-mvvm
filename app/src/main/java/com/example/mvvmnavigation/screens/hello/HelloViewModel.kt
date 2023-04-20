package com.example.mvvmnavigation.screens.hello

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmnavigation.R
import com.example.mvvmnavigation.navigator.Navigator
import com.example.mvvmnavigation.screens.base.BaseViewModel
import com.example.mvvmnavigation.screens.edit.EditFragment
import com.example.mvvmnavigation.screens.hello.HelloFragment.Screen


class HelloViewModel(
    private val navigator: Navigator,
    screen: Screen
) : BaseViewModel() {

    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData: LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.hello_world)
    }

    override fun onResult(result: Any) {
        if (result is String) {
            _currentMessageLiveData.value = result
        }
    }

    fun onEditPressed() {
        navigator.launch(EditFragment.Screen(initialValue = currentMessageLiveData.value!!))
    }
}
