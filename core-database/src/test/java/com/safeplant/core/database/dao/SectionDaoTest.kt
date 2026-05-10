package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.Section
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SectionDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var sectionDao: SectionDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        sectionDao = database.sectionDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetSection() = runBlocking {
        val section = Section(
            title = "Тестовый раздел",
            description = "Описание тестового раздела",
            order = 1
        )
        
        val id = sectionDao.insert(section)
        assertNotNull(id)
        
        val retrievedSection = sectionDao.getById(id)
        assertNotNull(retrievedSection)
        assertEquals(section.title, retrievedSection?.title)
        assertEquals(section.description, retrievedSection?.description)
        assertEquals(section.order, retrievedSection?.order)
    }

    @Test
    fun getRootSections() = runBlocking {
        val rootSection1 = Section(
            title = "Корневой раздел 1",
            order = 1
        )
        
        val rootSection2 = Section(
            title = "Корневой раздел 2",
            order = 2
        )
        
        val childSection = Section(
            title = "Дочерний раздел",
            parentId = rootSection1.id,
            order = 1
        )
        
        sectionDao.insert(rootSection1)
        sectionDao.insert(rootSection2)
        sectionDao.insert(childSection)
        
        val rootSections = sectionDao.getRootSections().first()
        assertEquals(2, rootSections.size)
        assertEquals(rootSection1.title, rootSections[0].title)
        assertEquals(rootSection2.title, rootSections[1].title)
    }

    @Test
    fun getChildSections() = runBlocking {
        val parentSection = Section(
            title = "Родительский раздел",
            order = 1
        )
        
        val childSection1 = Section(
            title = "Дочерний раздел 1",
            parentId = parentSection.id,
            order = 1
        )
        
        val childSection2 = Section(
            title = "Дочерний раздел 2",
            parentId = parentSection.id,
            order = 2
        )
        
        sectionDao.insert(parentSection)
        sectionDao.insert(childSection1)
        sectionDao.insert(childSection2)
        
        val childSections = sectionDao.getChildSections(parentSection.id).first()
        assertEquals(2, childSections.size)
        assertEquals(childSection1.title, childSections[0].title)
        assertEquals(childSection2.title, childSections[1].title)
    }

    @Test
    fun updateSection() = runBlocking {
        val section = Section(
            title = "Исходный заголовок",
            description = "Исходное описание",
            order = 1
        )
        
        val id = sectionDao.insert(section)
        val updatedSection = section.copy(
            title = "Обновленный заголовок",
            description = "Обновленное описание"
        )
        
        sectionDao.update(updatedSection)
        
        val retrievedSection = sectionDao.getById(id)
        assertEquals(updatedSection.title, retrievedSection?.title)
        assertEquals(updatedSection.description, retrievedSection?.description)
    }

    @Test
    fun deleteSection() = runBlocking {
        val section = Section(
            title = "Тестовый раздел",
            order = 1
        )
        
        val id = sectionDao.insert(section)
        sectionDao.delete(id)
        
        val retrievedSection = sectionDao.getById(id)
        assertEquals(null, retrievedSection)
    }
}