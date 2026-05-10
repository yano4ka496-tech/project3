package tests.unit

import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Тесты для шифрования данных
 * Проверка шифрования и расшифровки, а также обработка сбоя шифрования
 */
class TestEncryptedStorage {
    
    /**
     * Тест шифрования и расшифровки данных
     */
    @Test
    fun `test encryption and decryption`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Исходные данные
        val originalData = "Тестовые данные для шифрования"
        
        // Шифруем данные
        val encryptedData = encryptData(originalData, key)
        
        // Проверяем, что зашифрованные данные не равны исходным
        assertNotEquals(originalData, encryptedData)
        
        // Расшифровываем данные
        val decryptedData = decryptData(encryptedData, key)
        
        // Проверяем, что расшифрованные данные равны исходным
        assertEquals(originalData, decryptedData)
    }
    
    /**
     * Тест шифрования данных с разными ключами
     */
    @Test
    fun `test encryption with different keys`() {
        // Генерируем два разных ключа
        val key1 = generateSecretKey()
        val key2 = generateSecretKey()
        
        // Исходные данные
        val originalData = "Тестовые данные для шифрования"
        
        // Шифруем данные с первым ключом
        val encryptedData1 = encryptData(originalData, key1)
        
        // Шифруем данные со вторым ключом
        val encryptedData2 = encryptData(originalData, key2)
        
        // Проверяем, что зашифрованные данные с разными ключами не равны
        assertNotEquals(encryptedData1, encryptedData2)
        
        // Расшифровываем данные с первым ключом
        val decryptedData1 = decryptData(encryptedData1, key1)
        
        // Расшифровываем данные со вторым ключом
        val decryptedData2 = decryptData(encryptedData2, key2)
        
        // Проверяем, что расшифрованные данные равны исходным
        assertEquals(originalData, decryptedData1)
        assertEquals(originalData, decryptedData2)
    }
    
    /**
     * Тест шифрования и расшифровки пустых данных
     */
    @Test
    fun `test encryption and decryption of empty data`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Исходные данные (пустая строка)
        val originalData = ""
        
        // Шифруем данные
        val encryptedData = encryptData(originalData, key)
        
        // Проверяем, что зашифрованные данные не равны исходным
        assertNotEquals(originalData, encryptedData)
        
        // Расшифровываем данные
        val decryptedData = decryptData(encryptedData, key)
        
        // Проверяем, что расшифрованные данные равны исходным
        assertEquals(originalData, decryptedData)
    }
    
    /**
     * Тест шифрования и расшифровки данных с спецсимволами
     */
    @Test
    fun `test encryption and decryption of data with special characters`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Исходные данные (с спецсимволами)
        val originalData = "Тестовые данные с спецсимволами: !@#$%^&*()_+-=[]{}|;':\",./<>?"
        
        // Шифруем данные
        val encryptedData = encryptData(originalData, key)
        
        // Проверяем, что зашифрованные данные не равны исходным
        assertNotEquals(originalData, encryptedData)
        
        // Расшифровываем данные
        val decryptedData = decryptData(encryptedData, key)
        
        // Проверяем, что расшифрованные данные равны исходным
        assertEquals(originalData, decryptedData)
    }
    
    /**
     * Тест шифрования и расшифровки длинных данных
     */
    @Test
    fun `test encryption and decryption of long data`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Исходные данные (длинная строка)
        val originalData = "A".repeat(10000)
        
        // Шифруем данные
        val encryptedData = encryptData(originalData, key)
        
        // Проверяем, что зашифрованные данные не равны исходным
        assertNotEquals(originalData, encryptedData)
        
        // Расшифровываем данные
        val decryptedData = decryptData(encryptedData, key)
        
        // Проверяем, что расшифрованные данные равны исходным
        assertEquals(originalData, decryptedData)
    }
    
    /**
     * Тест обработки сбоя шифрования (неверный ключ)
     */
    @Test
    fun `test encryption failure with wrong key`() {
        // Генерируем два разных ключа
        val key1 = generateSecretKey()
        val key2 = generateSecretKey()
        
        // Исходные данные
        val originalData = "Тестовые данные для шифрования"
        
        // Шифруем данные с первым ключом
        val encryptedData = encryptData(originalData, key1)
        
        // Пытаемся расшифровать данные с вторым ключом
        try {
            decryptData(encryptedData, key2)
            fail("Должно выбросить исключение при расшифровке с неверным ключом")
        } catch (e: Exception) {
            // Ожидаемое исключение
            assertTrue(e is javax.crypto.AEADBadTagException)
        }
    }
    
    /**
     * Тест обработки сбоя шифрования (поврежденные данные)
     */
    @Test
    fun `test encryption failure with corrupted data`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Исходные данные
        val originalData = "Тестовые данные для шифрования"
        
        // Шифруем данные
        var encryptedData = encryptData(originalData, key)
        
        // Повреждаем данные (меняем один байт)
        val corruptedData = encryptedData.toByteArray().apply {
            this[0] = (this[0] + 1).toByte()
        }
        encryptedData = String(corruptedData)
        
        // Пытаемся расшифровать поврежденные данные
        try {
            decryptData(encryptedData, key)
            fail("Должно выбросить исключение при расшифровке поврежденных данных")
        } catch (e: Exception) {
            // Ожидаемое исключение
            assertTrue(e is javax.crypto.AEADBadTagException)
        }
    }
    
    /**
     * Тест обработки сбоя шифрования (пустые данные)
     */
    @Test
    fun `test encryption failure with empty encrypted data`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Пытаемся расшифровать пустые данные
        try {
            decryptData("", key)
            fail("Должно выбросить исключение при расшифровке пустых данных")
        } catch (e: Exception) {
            // Ожидаемое исключение
            assertTrue(e is IllegalArgumentException)
        }
    }
    
    /**
     * Тест обработки сбоя шифрования (null ключ)
     */
    @Test
    fun `test encryption failure with null key`() {
        // Исходные данные
        val originalData = "Тестовые данные для шифрования"
        
        // Пытаемся зашифровать данные с null ключом
        try {
            encryptData(originalData, null)
            fail("Должно выбросить исключение при шифровании с null ключом")
        } catch (e: Exception) {
            // Ожидаемое исключение
            assertTrue(e is IllegalArgumentException)
        }
    }
    
    /**
     * Тест обработки сбоя шифрования (null данные)
     */
    @Test
    fun `test encryption failure with null data`() {
        // Генерируем секретный ключ
        val key = generateSecretKey()
        
        // Пытаемся зашифровать null данные
        try {
            encryptData(null, key)
            fail("Должно выбросить исключение при шифровании null данных")
        } catch (e: Exception) {
            // Ожидаемое исключение
            assertTrue(e is IllegalArgumentException)
        }
    }
    
    /**
     * Генерация секретного ключа для шифрования
     */
    private fun generateSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256, SecureRandom())
        return keyGenerator.generateKey()
    }
    
    /**
     * Шифрование данных
     */
    private fun encryptData(data: String, key: SecretKey): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        
        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        
        // Сохраняем IV вместе с зашифрованными данными
        val combined = iv + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }
    
    /**
     * Расшифровка данных
     */
    private fun decryptData(encryptedData: String, key: SecretKey): String {
        val combined = Base64.getDecoder().decode(encryptedData)
        
        // Извлекаем IV и зашифрованные данные
        val iv = combined.copyOfRange(0, 12)
        val encryptedBytes = combined.copyOfRange(12, combined.size)
        
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)
        
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }
}