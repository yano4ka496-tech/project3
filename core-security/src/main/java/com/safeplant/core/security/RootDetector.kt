package com.safeplant.core.security

import android.content.Context

/**
 * Детектор root-доступа на устройстве.
 * Временная заглушка – позже будет реализована полностью.
 */
class RootDetector(private val context: Context) {
    fun isRooted(): Boolean {
        // TODO: реализовать проверку root (RootBeer и т.д.)
        return false
    }

    fun showRootWarningDialog(): Boolean {
        // TODO: показать диалог-предупреждение
        return false
    }
}
