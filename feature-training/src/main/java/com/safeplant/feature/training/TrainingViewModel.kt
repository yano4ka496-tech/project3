package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.security.PositionStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val positionStorage: PositionStorage
) : ViewModel() {

    private val _sections = MutableStateFlow(TrainingData.sections)
    val sections: StateFlow<List<TrainingSection>> = _sections.asStateFlow()

    private val _selectedSection = MutableStateFlow<TrainingSection?>(null)
    val selectedSection: StateFlow<TrainingSection?> = _selectedSection.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    init {
        loadFavorites()
    }

    fun selectSection(section: TrainingSection) {
        _selectedSection.value = section
    }

    fun navigateBack() {
        _selectedSection.value = null
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(sectionId: String) {
        viewModelScope.launch {
            val newFavorites = if (_favorites.value.contains(sectionId)) {
                _favorites.value - sectionId
            } else {
                _favorites.value + sectionId
            }
            _favorites.value = newFavorites
            saveFavorites(newFavorites)
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val favs = positionStorage.getFavorites() ?: emptySet()
            _favorites.value = favs.toSet()
        }
    }

    private suspend fun saveFavorites(favs: Set<String>) {
        positionStorage.saveFavorites(favs.toList())
    }

    fun getFilteredSections(): List<TrainingSection> {
        val query = _searchQuery.value.lowercase()
        return if (query.isBlank()) {
            _sections.value
        } else {
            _sections.value.filter {
                it.title.lowercase().contains(query) ||
                        it.content.lowercase().contains(query)
            }
        }
    }

    fun getFavoriteSections(): List<TrainingSection> {
        return _sections.value.filter { _favorites.value.contains(it.id) }
    }
}
