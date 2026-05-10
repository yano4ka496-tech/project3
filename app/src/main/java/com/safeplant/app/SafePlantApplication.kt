package com.safeplant.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.maplibre.android.MapLibre

@HiltAndroidApp
class SafePlantApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Инициализация MapLibre (для демо-тайлов)
        MapLibre.getInstance(this)
    }
}
