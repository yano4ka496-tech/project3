package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.BookmarkDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.entity.Bookmark
import com.safeplant.core.database.entity.TrainingTextContent
import com.safeplant.core.database.entity.TrainingVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана контента обучающего раздела
 */
@HiltViewModel
class ContentViewModel @Inject constructor(
    private val trainingTextContentDao: TrainingTextContentDao,
    private val trainingVideoDao: TrainingVideoDao,
    private val bookmarkDao: BookmarkDao
) : ViewModel() {
    
    private val _content = MutableStateFlow<TrainingTextContent?>(null)
    val content: StateFlow<TrainingTextContent?> = _content.asStateFlow()
    
    private val _video = MutableStateFlow<TrainingVideo?>(null)
    val video: StateFlow<TrainingVideo?> = _video.asStateFlow()
    
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    /**
     * Загрузка контента
     * @param contentId Идентификатор контента
     */
    fun loadContent(contentId: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Загружаем текстовый контент
                trainingTextContentDao.getById(contentId)?.let { content ->
                    _content.value = content
                    
                    // Проверяем, есть ли видео с таким же названием
                    trainingVideoDao.getAll().collect { videos ->
                        val video = videos.find { it.title == content.title }
                        _video.value = video
                    }
                    
                    // Проверяем, есть ли закладка для этого контента
                    bookmarkDao.hasBookmark(contentId).let { count ->
                        _isBookmarked.value = count > 0
                    }
                }
                
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки контента: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Переключение состояния закладки
     */
    fun toggleBookmark(contentId: Long) {
        viewModelScope.launch {
            if (_isBookmarked.value) {
                // Удаляем закладку
                bookmarkDao.deleteByContentId(contentId)
                _isBookmarked.value = false
            } else {
                // Добавляем закладку
                bookmarkDao.insert(Bookmark(contentId = contentId))
                _isBookmarked.value = true
            }
        }
    }
    
    /**
     * Обновление контента
     */
    fun refreshContent(contentId: Long) {
        _isLoading.value = true
        _error.value = null
        loadContent(contentId)
    }
}
--- END FILE -----

--- FILE: feature-training/src/main/java/com/safeplant/feature/training/BookmarksViewModel.kt ---
package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.BookmarkDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.Bookmark
import com.safeplant.core.database.entity.TrainingTextContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана закладок
 */
@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarkDao: BookmarkDao,
    private val trainingTextContentDao: TrainingTextContentDao
) : ViewModel() {
    
    private val _bookmarks = MutableStateFlow<List<Bookmark>>(emptyList())
    val bookmarks: StateFlow<List<Bookmark>> = _bookmarks.asStateFlow()
    
    private val _contentItems = MutableStateFlow<List<TrainingTextContent>>(emptyList())
    val contentItems: StateFlow<List<TrainingTextContent>> = _contentItems.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    init {
        loadBookmarks()
    }
    
    /**
     * Загрузка закладок
     */
    private fun loadBookmarks() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Комбинируем закладки и контент
                combine(
                    bookmarkDao.getAll(),
                    trainingTextContentDao.getAll()
                ) { bookmarks, contentItems ->
                    val contentMap = contentItems.associateBy { it.id }
                    bookmarks.map { bookmark ->
                        bookmark to contentMap[bookmark.contentId]
                    }
                }.collect { bookmarkContentPairs ->
                    _bookmarks.value = bookmarkContentPairs.map { it.first }
                    _contentItems.value = bookmarkContentPairs.mapNotNull { it.second }
                }
                
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки закладок: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Обновление закладок
     */
    fun refreshBookmarks() {
        _isLoading.value = true
        _error.value = null
        loadBookmarks()
    }
    
    /**
     * Установка поискового запроса
     */
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    /**
     * Удаление закладки
     */
    fun removeBookmark(bookmarkId: Long) {
        viewModelScope.launch {
            bookmarkDao.delete(bookmarkId)
            loadBookmarks()
        }
    }
}