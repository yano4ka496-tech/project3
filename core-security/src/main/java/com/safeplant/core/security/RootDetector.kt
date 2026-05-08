package com.safeplant.core.security

import android.content.Context

/**
 * Детектор root-доступа на устройстве.
 * Использует RootBeer для проверки.
 */
class RootDetector(private val context: Context) {

    /**
     * Проверяет наличие root-доступа на устройстве
     * @return true, если root-доступ обнаружен
     */
    fun isRooted(): Boolean {
        val rootBeer = com.scottyab.rootbeer.RootBeer(context)
        return rootBeer.isRooted()
    }

    /**
     * Показывает диалог-предупреждение о root-доступе
     * @return true, если диалог был показан
     */
    fun showRootWarningDialog(): Boolean {
        // В реальном приложении здесь будет показ диалога
        return true
    }
}
