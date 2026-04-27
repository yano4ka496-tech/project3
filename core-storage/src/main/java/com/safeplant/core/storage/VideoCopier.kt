package com.safeplant.core.storage

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class VideoCopier(private val context: Context) {
    
    private val internalStorageDir = context.filesDir
    
    /**
     * Копирует все видео из assets во внутреннее хранилище
     * @return true если все видео скопированы успешно, иначе false
     */
    suspend fun copyAllVideos(): Boolean = withContext(Dispatchers.IO) {
        val assetManager = context.assets
        val videoDir = File(internalStorageDir, "videos")
        
        // Создаем директорию для видео если ее нет
        if (!videoDir.exists()) {
            videoDir.mkdirs()
        }
        
        try {
            // Получаем список видео файлов в assets/videos
            val videoFiles = assetManager.list("videos") ?: return@withContext false
            
            var allCopied = true
            
            for (videoFile in videoFiles) {
                if (!videoFile.endsWith(".mp4")) continue
                
                val assetPath = "videos/$videoFile"
                val destinationPath = "${videoDir.absolutePath}/$videoFile"
                
                // Копируем файл только если его нет в хранилище
                if (!File(destinationPath).exists()) {
                    val assetManagerInstance = AssetManager(context)
                    if (!assetManagerInstance.copyAssetToInternalStorage(assetPath, destinationPath)) {
                        allCopied = false
                    }
                }
            }
            
            allCopied
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Получает путь к видео файлу во внутреннем хранилище
     * @param videoName Имя видео файла
     * @return Путь к видео или null если файл не найден
     */
    fun getVideoPath(videoName: String): String? {
        val videoFile = File("${internalStorageDir.absolutePath}/videos/$videoName")
        return if (videoFile.exists()) videoFile.absolutePath else null
    }
    
    /**
     * Проверяет существует ли видео файл во внутреннем хранилище
     * @param videoName Имя видео файла
     * @return true если файл существует, иначе false
     */
    fun isVideoDownloaded(videoName: String): Boolean {
        return getVideoPath(videoName) != null
    }
}