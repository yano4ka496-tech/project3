package com.safeplant.core.database.repository

import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.QuizResult
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Date

class QuizResultRepositoryTest {
    private val mockQuizResultDao = mockk<QuizResultDao>()
    private val repository = QuizResultRepository(mockQuizResultDao)

    @Test
    fun saveQuizResult() = runBlocking {
        val quizResult = QuizResult(
            userId = "user1",
            score = 8,
            passed = true,
            timestamp = Date()
        )
        
        every { mockQuizResultDao.insert(any()) } returns 1L
        
        val result = repository.saveQuizResult("user1", 8, true)
        
        assertEquals("user1", result.userId)
        assertEquals(8, result.score)
        assertEquals(true, result.passed)
    }

    @Test
    fun getUserQuizResults() = runBlocking {
        val quizResult1 = QuizResult(
            id = 1,
            userId = "user2",
            score = 7,
            passed = true,
            timestamp = Date()
        )
        
        val quizResult2 = QuizResult(
            id = 2,
            userId = "user2",
            score = 9,
            passed = true,
            timestamp = Date()
        )
        
        every { mockQuizResultDao.getByUser("user2") } returns listOf(quizResult2, quizResult1)
        
        val results = repository.getUserQuizResults("user2")
        
        assertEquals(2, results.size)
        assertEquals(quizResult2, results.first())
        assertEquals(quizResult1, results.last())
    }

    @Test
    fun getUserQuizResultsForPeriod() = runBlocking {
        val startDate = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)
        val endDate = Date()
        
        val quizResult = QuizResult(
            userId = "user3",
            score = 6,
            passed = false,
            timestamp = Date()
        )
        
        every { mockQuizResultDao.getByUserForPeriod("user3", startDate, endDate) } returns listOf(quizResult)
        
        val results = repository.getUserQuizResultsForPeriod("user3", startDate, endDate)
        
        assertEquals(1, results.size)
        assertEquals(quizResult, results.first())
    }

    @Test
    fun hasPassedQuizRecently() = runBlocking {
        val quizResult = QuizResult(
            userId = "user4",
            score = 8,
            passed = true,
            timestamp = Date()
        )
        
        every { mockQuizResultDao.getByUser("user4") } returns listOf(quizResult)
        
        val result = repository.hasPassedQuizRecently("user4")
        assertTrue(result)
    }

    @Test
    fun hasNotPassedQuizRecently() = runBlocking {
        val quizResult = QuizResult(
            userId = "user5",
            score = 6,
            passed = false,
            timestamp = Date()
        )
        
        every { mockQuizResultDao.getByUser("user5") } returns listOf(quizResult)
        
        val result = repository.hasPassedQuizRecently("user5")
        assertTrue(!result)
    }
}