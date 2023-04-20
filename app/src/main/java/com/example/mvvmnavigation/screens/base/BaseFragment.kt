package com.example.mvvmnavigation.screens.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    // View-модель, которая управляет этим фрагментом
    abstract val viewModel: BaseViewModel
}