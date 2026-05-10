package com.safeplant.core.storage

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException

/**
 * Проверщик общего размера видео в assets
 * Проверяет, что общий размер всех видео файлов не превышает 150 МБ
 */
class VideoSizeChecker(private val context: Context) {
    companion object {
        // Максимальный разрешенный размер видео: 150 МБ
        private const val MAX_SIZE_BYTES = 150 * 1024 * 1024L
    }

    /**
     * Проверяет общий размер видео в assets/videos
     * @return Result с общим размером в байтах или ошибкой
     */
    suspend fun checkTotalSize(): Result<Long> = withIO {
        val assetManager = context.assets
        return@withIO try {
            val videoFiles = assetManager.list("videos") ?: return@withIO Result.success(0L)
            var totalSize = 0L

            for (videoFile in videoFiles) {
                if (!videoFile.endsWith(".mp4")) continue

                val assetPath = "videos/$videoFile"
                val inputStream = assetManager.open(assetPath)
                totalSize += inputStream.available()
                inputStream.close()
            }

            if (totalSize > MAX_SIZE_BYTES) {
                Result.failure(IllegalStateException("Общий размер видео превышает 150 МБ"))
            } else {
                Result.success(totalSize)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Проверяет, есть ли видео в assets
     * @return true если есть хотя бы одно видео, иначе false
     */
    suspend fun hasVideos(): Boolean = withIO {
        val assetManager = context.assets
        try {
            val videoFiles = assetManager.list("videos") ?: return@withIO false
            videoFiles.any { it.endsWith(".mp4") }
        } catch (e: IOException) {
            false
        }
    }

    /**
     * Возвращает список видео файлов в assets
     * @return Список имен файлов или пустой список при ошибке
     */
    suspend fun getVideoFiles(): List<String> = withIO {
        val assetManager = context.assets
        try {
            val videoFiles = assetManager.list("videos") ?: return@withIO emptyList()
            videoFiles.filter { it.endsWith(".mp4") }
        } catch (e: IOException) {
            emptyList()
        }
    }

    /**
     * Вспомогательная функция для выполнения операций в IO-контексте
     */
    private suspend fun <T> withIO(block: suspend () -> T): T {
        return kotlinx.coroutines.Dispatchers.IO.runBlocking {
            block()
        }
    }
}