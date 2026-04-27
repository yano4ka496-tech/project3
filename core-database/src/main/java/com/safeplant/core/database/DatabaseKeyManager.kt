package com.safeplant.core.database

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class DatabaseKeyManager(context: Context) {
    private val appContext = context.applicationContext
    private val prefs = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private val keyStoreAlias = "sqlcipher_keystore_key"
    private val prefsKey = "encrypted_db_key"

    fun getKey(): ByteArray {
        val encryptedKeyString = prefs.getString(prefsKey, null)
        return if (encryptedKeyString == null) {
            generateAndStoreNewKey()
        } else {
            decryptKey(encryptedKeyString)
        }
    }

    private fun generateAndStoreNewKey(): ByteArray {
        val secretKey = getOrCreateKeystoreKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val newKey = ByteArray(32).also { SecureRandom().nextBytes(it) }
        val encryptedKeyBytes = cipher.doFinal(newKey)
        val encodedKey = Base64.getEncoder().encodeToString(encryptedKeyBytes)
        val iv = Base64.getEncoder().encodeToString(cipher.iv)
        prefs.edit().putString(prefsKey, "$encodedKey|$iv").apply()
        return newKey
    }

    private fun getOrCreateKeystoreKey(): SecretKey {
        val keyStore = java.security.KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }
        return if (keyStore.containsAlias(keyStoreAlias)) {
            (keyStore.getKey(keyStoreAlias, null) as SecretKey)
        } else {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val spec = KeyGenParameterSpec.Builder(
                keyStoreAlias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }
    }

    private fun decryptKey(encryptedDataString: String): ByteArray {
        val (encryptedKeyString, ivString) = encryptedDataString.split("|")
        val secretKey = getOrCreateKeystoreKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val iv = Base64.getDecoder().decode(ivString)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        val encryptedKeyBytes = Base64.getDecoder().decode(encryptedKeyString)
        return cipher.doFinal(encryptedKeyBytes)
    }
}
