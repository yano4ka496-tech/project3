package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.entity.Section
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана обучающего центра
 */
@HiltViewModel
class TrainingCenterViewModel @Inject constructor(
    private val sectionDao: SectionDao
) : ViewModel() {
    
    private val _sections = MutableStateFlow<List<Section>>(emptyList())
    val sections: StateFlow<List<Section>> = _sections.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    init {
        loadSections()
    }
    
    /**
     * Загрузка разделов из базы данных
     */
    private fun loadSections() {
        viewModelScope.launch {
            try {
                sectionDao.getRootSections().collect { sections ->
                    _sections.value = sections
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки разделов: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Обновление списка разделов
     */
    fun refreshSections() {
        _isLoading.value = true
        _error.value = null
        loadSections()
    }
}