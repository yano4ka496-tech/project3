package com.safeplant.core.database.service

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.Section
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TrainingContentSearchTest {
    private lateinit var database: AppDatabase
    private lateinit var sectionDao: SectionDao
    private lateinit var trainingTextContentDao: TrainingTextContentDao
    private lateinit var trainingContentSearch: TrainingContentSearch

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        sectionDao = database.sectionDao()
        trainingTextContentDao = database.trainingTextContentDao()
        trainingContentSearch = TrainingContentSearch(sectionDao, trainingTextContentDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun searchInSections() = runBlocking {
        // Создаем тестовые разделы
        val section1 = Section(
            title = "Общие правила безопасности",
            description = "Основные правила безопасности на предприятии",
            order = 1
        )
        
        val section2 = Section(
            title = "Чрезвычайные ситуации",
            description = "Процедуры при пожарах и авариях",
            order = 2
        )
        
        sectionDao.insert(section1)
        sectionDao.insert(section2)
        
        // Ищем по разделам
        val results = trainingContentSearch.searchInSections("безопасность")
        assertEquals(1, results.size)
        assertEquals(section1.title, results[0].title)
        
        val results2 = trainingContentSearch.searchInSections("пожар")
        assertEquals(1, results2.size)
        assertEquals(section2.title, results2[0].title)
    }

    @Test
    fun searchInContent() = runBlocking {
        // Создаем тестовый контент
        val content1 = TrainingTextContent(
            sectionId = 1,
            title = "Использование СИЗ",
            content = "При работе на производстве необходимо использовать средства индивидуальной защиты: каски, очки, перчатки.",
            order = 1
        )
        
        val content2 = TrainingTextContent(
            sectionId = 1,
            title = "Порядок действий при аварии",
            content = "При обнаружении утечки газа немедленно покиньте помещение и сообщите ответственному лицу.",
            order = 2
        )
        
        trainingTextContentDao.insert(content1)
        trainingTextContentDao.insert(content2)
        
        // Ищем по контенту
        val results = trainingContentSearch.searchInContent("газ")
        assertEquals(1, results.size)
        assertEquals(content2.title, results[0].title)
        
        val results2 = trainingContentSearch.searchInContent("СИЗ")
        assertEquals(1, results2.size)
        assertEquals(content1.title, results2[0].title)
    }

    @Test
    fun searchInAllContent() = runBlocking {
        // Создаем тестовые данные
        val section1 = Section(
            title = "Общие правила",
            order = 1
        )
        
        val section2 = Section(
            title = "Чрезвычайные ситуации",
            order = 2
        )
        
        sectionDao.insert(section1)
        sectionDao.insert(section2)
        
        val content1 = TrainingTextContent(
            sectionId = section1.id,
            title = "Использование СИЗ",
            content = "При работе на производстве необходимо использовать средства индивидуальной защиты: каски, очки, перчатки.",
            order = 1
        )
        
        val content2 = TrainingTextContent(
            sectionId = section2.id,
            title = "Порядок действий при аварии",
            content = "При обнаружении утечки газа немедленно покиньте помещение и сообщите ответственному лицу.",
            order = 1
        )
        
        trainingTextContentDao.insert(content1)
        trainingTextContentDao.insert(content2)
        
        // Ищем по всему контенту
        val results = trainingContentSearch.searchInAllContent("газ")
        assertEquals(1, results.size)
        assertEquals(content2.title, results[0].title)
        
        val results2 = trainingContentSearch.searchInAllContent("СИЗ")
        assertEquals(1, results2.size)
        assertEquals(content1.title, results2[0].title)
        
        val results3 = trainingContentSearch.searchInAllContent("производство")
        assertEquals(1, results3.size)
        assertEquals(content1.title, results3[0].title)
    }

    @Test
    fun searchCaseInsensitive() = runBlocking {
        // Создаем тестовый контент
        val content = TrainingTextContent(
            sectionId = 1,
            title = "Правила безопасности",
            content = "При работе с химикатами используйте защитную одежду.",
            order = 1
        )
        
        trainingTextContentDao.insert(content)
        
        // Ищем с разным регистром
        val results1 = trainingContentSearch.searchInContent("химикатами")
        assertEquals(1, results1.size)
        
        val results2 = trainingContentSearch.searchInContent("ХИМИКАТАМИ")
        assertEquals(1, results2.size)
        
        val results3 = trainingContentSearch.searchInContent("Химикатами")
        assertEquals(1, results3.size)
    }
}