package com.safeplant.core.storage

import android.content.Context
import android.content.res.AssetManager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class AssetManager(private val context: Context) {
    private val assetManager: AssetManager = context.assets

    /**
     * Копирует файл из assets во внутреннее хранилище приложения
     * @param assetPath Путь к файлу в assets
     * @param destinationPath Путь назначения во внутреннем хранилище
     * @return true если копирование успешно, иначе false
     */
    fun copyAssetToInternalStorage(
        assetPath: String,
        destinationPath: String,
    ): Boolean {
        try {
            val inputStream: InputStream = assetManager.open(assetPath)
            val outFile = File(destinationPath)

            // Создаем директории если их нет
            outFile.parentFile?.mkdirs()

            val outputStream = FileOutputStream(outFile)

            val buffer = ByteArray(1024)
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }

            inputStream.close()
            outputStream.flush()
            outputStream.close()

            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    /**
     * Проверяет существует ли файл во внутреннем хранилище
     * @param filePath Путь к файлу
     * @return true если файл существует, иначе false
     */
    fun fileExists(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists()
    }

    /**
     * Получает список файлов в директории assets
     * @param path Путь к директории в assets
     * @return Список файлов
     */
    fun getAssetFiles(path: String): Array<String>? {
        return try {
            assetManager.list(path)
        } catch (e: IOException) {
            null
        }
    }
}
