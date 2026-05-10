package com.safeplant.ui

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Кастомный IdlingResource для ожидания загрузки карты
 * Позволяет синхронизировать тесты с процессом загрузки карты
 */
class CustomIdlingResource private constructor(
    private val resourceName: String
) : IdlingResource {

    private val isIdleNow = AtomicBoolean(true)
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean = isIdleNow.get()

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    /**
     * Устанавливает состояние ресурса в "занято"
     */
    fun setIdleState(idle: Boolean) {
        val wasIdle = isIdleNow.get()
        isIdleNow.set(idle)
        
        if (!wasIdle && idle && resourceCallback != null) {
            resourceCallback!!.onTransitionToIdle()
        }
    }

    /**
     * Создает экземпляр IdlingResource
     */
    companion object {
        fun createMapLoadingIdlingResource(): CustomIdlingResource {
            return CustomIdlingResource("MAP_LOADING")
        }
    }
}