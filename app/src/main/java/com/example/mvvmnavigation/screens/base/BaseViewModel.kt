package com.example.mvvmnavigation.screens.base

import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {
//Переопределяем в каждой VM этот метод,
// для прослушивания результатов с других экранов
    open fun onResult(result: Any) {
    }
}