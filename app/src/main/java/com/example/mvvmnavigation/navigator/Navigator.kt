package com.example.mvvmnavigation.navigator

import androidx.annotation.StringRes
import com.example.mvvmnavigation.screens.base.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen)

    fun goBack(result: Any? = null)

    fun toast(@StringRes messageRes: Int)

    fun getString(@StringRes messageRes: Int): String

}