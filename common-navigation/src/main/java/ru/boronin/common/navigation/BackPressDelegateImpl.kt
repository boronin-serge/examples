package ru.boronin.common.navigation

import ru.boronin.core.api.navigator.BackListener

/**
 * Created by Sergey Boronin on 04.02.2020.
 */
class BackPressDelegateImpl : BackPressDelegate {
    private lateinit var navigator: AppNavigatorHandlerImpl

    override fun attachNavigator(navigator: AppNavigatorHandlerImpl) {
        this.navigator = navigator
    }

    override fun backPressed(superFun: () -> Unit) {
        val backListener = navigator.getLastFragment() as? BackListener
        val handleBack = backListener?.back() ?: false
        if (handleBack) {
            superFun.invoke()
        }
    }
}