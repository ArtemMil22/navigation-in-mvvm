package com.example.mvvmnavigation.navigator

import com.example.mvvmnavigation.MainActivity

typealias MainActivityAction = (MainActivity) -> Unit

class MainActivityActions {

    // все действия которые будут сюда приходить они
    // будут выполняться с актуальной активити,
    // (выступает как очередь вызова действий)

    var mainActivity: MainActivity? = null
        set(activity) {
            field = activity
            if (activity != null) {
                actions.forEach { it(activity) }
                actions.clear()
            }
        }
    //.

    private val actions = mutableListOf<MainActivityAction>()

    // внутри определим логику выполнения действий
    operator fun invoke(action: MainActivityAction) {
        val activity = this.mainActivity
        if (activity == null) {
            actions += action
        } else {
            action(activity)
        }
    }

    fun clear() {
        actions.clear()
    }

}