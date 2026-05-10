package com.safeplant.core.database.service

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.Section
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class TrainingContentLoaderTest {
    private lateinit var database: AppDatabase
    private lateinit var sectionDao: SectionDao
    private lateinit var trainingTextContentDao: TrainingTextContentDao
    private lateinit var trainingContentLoader: TrainingContentLoader

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        sectionDao = database.sectionDao()
        trainingTextContentDao = database.trainingTextContentDao()
        trainingContentLoader = TrainingContentLoader(context, sectionDao, trainingTextContentDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun loadSectionsFromJson() = runBlocking {
        // Создаем тестовый JSON файл
        val jsonContent = """
            [
                {
                    "title": "Общие правила",
                    "description": "Основные правила безопасности",
                    "order": 1
                },
                {
                    "title": "Чрезвычайные ситуации",
                    "description": "Процедуры при ЧС",
                    "order": 2,
                    "parentId": 1
                }
            ]
        """.trimIndent()

        // Загружаем разделы
        trainingContentLoader.loadSectionsFromJson(jsonContent)

        // Проверяем результат
        val rootSections = sectionDao.getRootSections().first()
        assertEquals(1, rootSections.size)
        assertEquals("Общие правила", rootSections[0].title)

        val childSections = sectionDao.getChildSections(1).first()
        assertEquals(1, childSections.size)
        assertEquals("Чрезвычайные ситуации", childSections[0].title)
    }

    @Test
    fun loadContentFromMarkdown() = runBlocking {
        // Создаем тестовый Markdown контент
        val markdownContent = """
            # Заголовок раздела
            
            Это основной текст раздела.
            
            ## Подраздел
            
            Текст подраздела.
        """.trimIndent()

        // Загружаем контент
        val sectionId = 1L
        trainingContentLoader.loadContentFromMarkdown(sectionId, "Тестовый контент", markdownContent)

        // Проверяем результат
        val contentItems = trainingTextContentDao.getBySectionId(sectionId).first()
        assertEquals(1, contentItems.size)
        assertEquals("Тестовый контент", contentItems[0].title)
        assertEquals(markdownContent, contentItems[0].content)
    }

    @Test
    fun loadFullTrainingContent() = runBlocking {
        // Создаем тестовые данные
        val sectionsJson = """
            [
                {
                    "title": "Общие правила",
                    "description": "Основные правила безопасности",
                    "order": 1
                },
                {
                    "title": "Чрезвычайные ситуации",
                    "description": "Процедуры при ЧС",
                    "order": 2,
                    "parentId": 1
                }
            ]
        """.trimIndent()

        val markdownContent = """
            # Заголовок раздела
            
            Это основной текст раздела.
        """.trimIndent()

        // Загружаем контент
        trainingContentLoader.loadSectionsFromJson(sectionsJson)
        trainingContentLoader.loadContentFromMarkdown(1, "Тестовый контент", markdownContent)

        // Проверяем результат
        val rootSections = sectionDao.getRootSections().first()
        assertEquals(1, rootSections.size)
        assertEquals("Общие правила", rootSections[0].title)

        val childSections = sectionDao.getChildSections(1).first()
        assertEquals(1, childSections.size)
        assertEquals("Чрезвычайные ситуации", childSections[0].title)

        val contentItems = trainingTextContentDao.getBySectionId(1).first()
        assertEquals(1, contentItems.size)
        assertEquals("Тестовый контент", contentItems[0].title)
    }
}