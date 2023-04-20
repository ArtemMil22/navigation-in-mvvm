package com.example.mvvmnavigation.screens.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmnavigation.Event
import com.example.mvvmnavigation.R
import com.example.mvvmnavigation.navigator.Navigator
import com.example.mvvmnavigation.screens.base.BaseViewModel
import com.example.mvvmnavigation.screens.edit.EditFragment.Screen

class EditViewModel(
    private val navigator: Navigator,
    screen: Screen
) : BaseViewModel() {

    private val _initialMessageEvent = MutableLiveData<Event<String>>()
    val initialMessageEvent: LiveData<Event<String>> = _initialMessageEvent

    init {
        // sending initial value from screen argument to the fragment
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message: String) {
        if (message.isBlank()) {
            navigator.toast(R.string.empty_message)
            return
        }
        navigator.goBack(message)
    }

    fun onCancelPressed() {
        navigator.goBack()
    }

}