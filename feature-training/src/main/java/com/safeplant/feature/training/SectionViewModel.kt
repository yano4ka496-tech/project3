package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.Section
import com.safeplant.core.database.entity.TrainingTextContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана раздела обучающего контента
 */
@HiltViewModel
class SectionViewModel @Inject constructor(
    private val sectionDao: SectionDao,
    private val trainingTextContentDao: TrainingTextContentDao
) : ViewModel() {
    
    private val _section = MutableStateFlow<Section?>(null)
    val section: StateFlow<Section?> = _section.asStateFlow()
    
    private val _childSections = MutableStateFlow<List<Section>>(emptyList())
    val childSections: StateFlow<List<Section>> = _childSections.asStateFlow()
    
    private val _contentItems = MutableStateFlow<List<TrainingTextContent>>(emptyList())
    val contentItems: StateFlow<List<TrainingTextContent>> = _contentItems.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    /**
     * Загрузка данных раздела
     * @param sectionId Идентификатор раздела
     */
    fun loadSection(sectionId: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Загружаем информацию о разделе
                sectionDao.getById(sectionId)?.let { section ->
                    _section.value = section
                    
                    // Загружаем дочерние разделы
                    sectionDao.getChildSections(sectionId).collect { childSections ->
                        _childSections.value = childSections
                    }
                    
                    // Загружаем контент раздела
                    trainingTextContentDao.getBySectionId(sectionId).collect { contentItems ->
                        _contentItems.value = contentItems
                    }
                }
                
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки раздела: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Обновление данных раздела
     */
    fun refreshSection(sectionId: Long) {
        _isLoading.value = true
        _error.value = null
        loadSection(sectionId)
    }
}