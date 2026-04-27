package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Date
import java.util.Calendar

class QuizResultDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var quizResultDao: QuizResultDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        quizResultDao = database.quizResultDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetQuizResult() = runBlocking {
        val quizResult = QuizResult(
            userId = "user1",
            score = 8,
            passed = true,
            timestamp = Date()
        )
        
        val id = quizResultDao.insert(quizResult)
        val retrievedResults = quizResultDao.getByUser("user1")
        
        assertEquals(1, retrievedResults.size)
        assertEquals(id, retrievedResults.first().id)
        assertEquals("user1", retrievedResults.first().userId)
        assertEquals(8, retrievedResults.first().score)
        assertEquals(true, retrievedResults.first().passed)
    }

    @Test
    fun getQuizResultsForPeriod() = runBlocking {
        val calendar = Calendar.getInstance()
        val startDate = calendar.time
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endDate = calendar.time
        
        val quizResult1 = QuizResult(
            userId = "user2",
            score = 7,
            passed = true,
            timestamp = startDate
        )
        
        val quizResult2 = QuizResult(
            userId = "user2",
            score = 9,
            passed = true,
            timestamp = endDate
        )
        
        quizResultDao.insert(quizResult1)
        quizResultDao.insert(quizResult2)
        
        val results = quizResultDao.getByUserForPeriod("user2", startDate, endDate)
        assertEquals(2, results.size)
    }

    @Test
    fun getQuizResultsOutsidePeriod() = runBlocking {
        val calendar = Calendar.getInstance()
        val startDate = calendar.time
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endDate = calendar.time
        
        val quizResult = QuizResult(
            userId = "user3",
            score = 6,
            passed = false,
            timestamp = Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000)
        )
        
        quizResultDao.insert(quizResult)
        
        val results = quizResultDao.getByUserForPeriod("user3", startDate, endDate)
        assertTrue(results.isEmpty())
    }
}