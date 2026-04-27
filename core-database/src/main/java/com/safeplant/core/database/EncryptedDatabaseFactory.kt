package com.safeplant.core.database

import android.content.Context
import androidx.sqlite.db.SupportFactory
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.io.File

/**
 * Фабрика для создания зашифрованной базы данных с использованием SQLCipher
 * Обеспечивает обработку потери ключа шифрования
 */
class EncryptedDatabaseFactory(private val keyManager: DatabaseKeyManager) : SupportFactory {
    
    override fun create(dbFile: File): SupportSQLiteDatabase {
        val key = keyManager.getKey()
        return SQLiteDatabase.openOrCreateDatabase(
            dbFile,
            key.toString(),
            null
        )
    }
    
    /**
     * Обработка потери ключа шифрования
     * При потере ключа удаляет старую базу данных и создает новую
     */
    fun handleKeyLoss(context: Context) {
        val dbFile = context.getDatabasePath("safeplant_database")
        if (dbFile.exists()) {
            dbFile.delete()
        }
        // База данных будет создана заново при следующем обращении
    }
}