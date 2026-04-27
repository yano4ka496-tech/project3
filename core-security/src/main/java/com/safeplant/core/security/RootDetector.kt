package com.safeplant.core.security

import android.content.Context
import android.os.Build
import com.scottyab.rootbeer.RootBeer

class RootDetector(private val context: Context) {
    
    fun isRooted(): Boolean {
        val rootBeer = RootBeer(context)
        
        // Проверка через RootBeer
        if (rootBeer.isRooted) {
            return true
        }
        
        // Дополнительные проверки для надежности
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val paths = arrayOf(
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
            )
            
            for (path in paths) {
                val file = java.io.File(path)
                if (file.exists()) {
                    return true
                }
            }
        }
        
        return false
    }
    
    fun showRootWarningDialog(): Boolean {
        return isRooted()
    }
}