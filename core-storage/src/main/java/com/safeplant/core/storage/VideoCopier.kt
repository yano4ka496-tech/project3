package com.safeplant.core.storage

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Копирует видео из assets во внутреннее хранилище с проверкой размера и обработкой ошибок
 */
class VideoCopier(private val context: Context) {
    private val internalStorageDir = context.filesDir
    private val videoSizeChecker = VideoSizeChecker(context)

    /**
     * Результат копирования видео
     * @param success true если копирование выполнено (даже с ошибками), false если не выполнено (например, из-за размера)
     * @param errorMessages список сообщений об ошибках
     */
    data class CopyResult(
        val success: Boolean,
        val errorMessages: List<String> = emptyList()
    )

    /**
     * Копирует все видео из assets во внутреннее хранилище
     * @return CopyResult с результатом и сообщениями об ошибках
     */
    suspend fun copyAllVideos(): CopyResult = withContext(Dispatchers.IO) {
        // Проверка наличия видео в assets
        if (!videoSizeChecker.hasVideos()) {
            return@withContext CopyResult(success = true, errorMessages = emptyList())
        }

        // Проверка общего размера
        val sizeCheckResult = videoSizeChecker.checkTotalSize()
        if (sizeCheckResult.isFailure) {
            val errorMessage = "Ошибка проверки размера: ${sizeCheckResult.exceptionOrNull()?.message}"
            return@withContext CopyResult(success = false, errorMessages = listOf(errorMessage))
        }

        // Если размер превышен, не копируем
        if (sizeCheckResult.getOrNull()!! > VideoSizeChecker.MAX_SIZE_BYTES) {
            return@withContext CopyResult(
                success = false,
                errorMessages = listOf("Общий размер видео превышает 150 МБ")
            )
        }

        // Копирование файлов
        val assetManager = context.assets
        val videoDir = File(internalStorageDir, "videos")

        // Создаем директорию для видео если ее нет
        if (!videoDir.exists()) {
            videoDir.mkdirs()
        }

        val videoFiles = videoSizeChecker.getVideoFiles()
        val errorMessages = mutableListOf<String>()
        var successCount = 0

        for (videoFile in videoFiles) {
            val assetPath = "videos/$videoFile"
            val destinationPath = "${videoDir.absolutePath}/$videoFile"

            // Копируем файл только если его нет в хранилище
            if (File(destinationPath).exists()) {
                successCount++
                continue
            }

            try {
                val assetManagerInstance = AssetManager(context)
                if (!assetManagerInstance.copyAssetToInternalStorage(assetPath, destinationPath)) {
                    errorMessages.add("Не удалось скопировать файл: $videoFile")
                } else {
                    successCount++
                }
            } catch (e: Exception) {
                errorMessages.add("Ошибка копирования файла $videoFile: ${e.message}")
            }
        }

        // Если есть ошибки, возвращаем их, но копирование считается успешным
        if (errorMessages.isNotEmpty()) {
            return@withContext CopyResult(success = true, errorMessages = errorMessages)
        }

        CopyResult(success = true, errorMessages = emptyList())
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