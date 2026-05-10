package com.safeplant.core.database.dao

import com.safeplant.core.database.entity.AccessPass
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessPassDaoInMemory @Inject constructor() : AccessPassDao {
    private var currentPass: AccessPass? = null

    override suspend fun insert(accessPass: AccessPass) {
        currentPass = accessPass
    }

    override suspend fun insertOrUpdate(accessPass: AccessPass) {
        currentPass = accessPass
    }

    override suspend fun getValidAccessPass(userId: String, currentTime: Long): AccessPass? {
        val pass = currentPass
        return if (pass != null && pass.isValid && pass.expiryDate > currentTime) pass else null
    }

    override suspend fun getValidById(id: Long, currentTime: Long): AccessPass? {
        val pass = currentPass
        return if (pass != null && pass.id == id && pass.isValid && pass.expiryDate > currentTime) pass else null
    }

    override suspend fun getAllValid(currentTime: Long): List<AccessPass> {
        val pass = currentPass
        return if (pass != null && pass.isValid && pass.expiryDate > currentTime) listOf(pass) else emptyList()
    }

    override suspend fun invalidateAccessPass(accessPassId: Long) {
        currentPass?.let { if (it.id == accessPassId) it.isValid = false }
    }

    override suspend fun deleteAll() {
        currentPass = null
    }

    override suspend fun hasValidAccessPass(userId: String, currentTime: Long): Boolean {
        val pass = currentPass
        return pass != null && pass.isValid && pass.expiryDate > currentTime
    }

    override suspend fun resetAllAccessPasses() {
        currentPass = null
    }
}
