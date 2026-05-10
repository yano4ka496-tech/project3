package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.Bookmark
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var bookmarkDao: BookmarkDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        bookmarkDao = database.bookmarkDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetBookmark() = runBlocking {
        val bookmark = Bookmark(
            contentId = 1,
            createdAt = System.currentTimeMillis()
        )
        
        val id = bookmarkDao.insert(bookmark)
        assertTrue(id > 0)
        
        val bookmarks = bookmarkDao.getAll().first()
        assertEquals(1, bookmarks.size)
        assertEquals(bookmark.contentId, bookmarks[0].contentId)
    }

    @Test
    fun getBookmarkByContentId() = runBlocking {
        val contentId = 1L
        
        val bookmark1 = Bookmark(
            contentId = contentId,
            createdAt = System.currentTimeMillis()
        )
        
        val bookmark2 = Bookmark(
            contentId = 2,
            createdAt = System.currentTimeMillis()
        )
        
        bookmarkDao.insert(bookmark1)
        bookmarkDao.insert(bookmark2)
        
        val bookmarks = bookmarkDao.getByContentId(contentId)
        assertEquals(1, bookmarks.size)
        assertEquals(bookmark1.contentId, bookmarks[0].contentId)
    }

    @Test
    fun hasBookmark() = runBlocking {
        val contentId = 1L
        
        // Проверяем отсутствие закладки
        assertEquals(0, bookmarkDao.hasBookmark(contentId))
        
        // Добавляем закладку
        val bookmark = Bookmark(
            contentId = contentId,
            createdAt = System.currentTimeMillis()
        )
        bookmarkDao.insert(bookmark)
        
        // Проверяем наличие закладки
        assertEquals(1, bookmarkDao.hasBookmark(contentId))
    }

    @Test
    fun deleteBookmark() = runBlocking {
        val bookmark = Bookmark(
            contentId = 1,
            createdAt = System.currentTimeMillis()
        )
        
        val id = bookmarkDao.insert(bookmark)
        bookmarkDao.delete(id)
        
        val bookmarks = bookmarkDao.getAll().first()
        assertTrue(bookmarks.isEmpty())
    }

    @Test
    fun deleteByContentId() = runBlocking {
        val contentId = 1L
        
        val bookmark1 = Bookmark(
            contentId = contentId,
            createdAt = System.currentTimeMillis()
        )
        
        val bookmark2 = Bookmark(
            contentId = contentId,
            createdAt = System.currentTimeMillis()
        )
        
        bookmarkDao.insert(bookmark1)
        bookmarkDao.insert(bookmark2)
        
        bookmarkDao.deleteByContentId(contentId)
        
        val bookmarks = bookmarkDao.getAll().first()
        assertTrue(bookmarks.isEmpty())
    }
}