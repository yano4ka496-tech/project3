package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrainingTextContentDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var trainingTextContentDao: TrainingTextContentDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        trainingTextContentDao = database.trainingTextContentDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetContent() = runBlocking {
        val content = TrainingTextContent(
            sectionId = 1,
            title = "Тестовый контент",
            content = "# Заголовок\n\nТекст контента",
            order = 1
        )
        
        val id = trainingTextContentDao.insert(content)
        assertNotNull(id)
        
        val retrievedContent = trainingTextContentDao.getById(id)
        assertNotNull(retrievedContent)
        assertEquals(content.title, retrievedContent?.title)
        assertEquals(content.content, retrievedContent?.content)
        assertEquals(content.order, retrievedContent?.order)
    }

    @Test
    fun getContentBySectionId() = runBlocking {
        val sectionId = 1L
        
        val content1 = TrainingTextContent(
            sectionId = sectionId,
            title = "Контент 1",
            content = "Текст контента 1",
            order = 1
        )
        
        val content2 = TrainingTextContent(
            sectionId = sectionId,
            title = "Контент 2",
            content = "Текст контента 2",
            order = 2
        )
        
        trainingTextContentDao.insert(content1)
        trainingTextContentDao.insert(content2)
        
        val sectionContents = trainingTextContentDao.getBySectionId(sectionId).first()
        assertEquals(2, sectionContents.size)
        assertEquals(content1.title, sectionContents[0].title)
        assertEquals(content2.title, sectionContents[1].title)
    }

    @Test
    fun updateContent() = runBlocking {
        val content = TrainingTextContent(
            sectionId = 1,
            title = "Исходный заголовок",
            content = "Исходный текст",
            order = 1
        )
        
        val id = trainingTextContentDao.insert(content)
        val updatedContent = content.copy(
            title = "Обновленный заголовок",
            content = "Обновленный текст"
        )
        
        trainingTextContentDao.update(updatedContent)
        
        val retrievedContent = trainingTextContentDao.getById(id)
        assertEquals(updatedContent.title, retrievedContent?.title)
        assertEquals(updatedContent.content, retrievedContent?.content)
    }

    @Test
    fun deleteContent() = runBlocking {
        val content = TrainingTextContent(
            sectionId = 1,
            title = "Тестовый контент",
            content = "Текст контента",
            order = 1
        )
        
        val id = trainingTextContentDao.insert(content)
        trainingTextContentDao.delete(id)
        
        val retrievedContent = trainingTextContentDao.getById(id)
        assertEquals(null, retrievedContent)
    }
}