package com.safeplant.feature.training

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen(viewModel: TrainingViewModel = hiltViewModel()) {
    val selectedSection by viewModel.selectedSection.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (selectedSection == null) "Обучение" else selectedSection?.title ?: "") },
                navigationIcon = {
                    if (selectedSection != null) {
                        IconButton(onClick = { viewModel.navigateBack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                        }
                    }
                },
                actions = {
                    if (selectedSection == null) {
                        IconButton(onClick = { /* поиск уже есть в списке */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Поиск")
                        }
                    } else {
                        IconButton(onClick = {
                            selectedSection?.let { viewModel.toggleFavorite(it.id) }
                        }) {
                            val isFavorite = favorites.contains(selectedSection?.id)
                            Icon(
                                if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = if (isFavorite) "Удалить из закладок" else "В закладки"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (selectedSection == null) {
                TrainingListScreen(viewModel)
            } else {
                TrainingDetailScreen(selectedSection!!)
            }
        }
    }
}

@Composable
fun TrainingListScreen(viewModel: TrainingViewModel) {
    var searchText by remember { mutableStateOf("") }
    var showFavoritesOnly by remember { mutableStateOf(false) }

    val filteredSections = if (showFavoritesOnly) {
        viewModel.getFavoriteSections()
    } else {
        if (searchText.isBlank()) viewModel.sections.value else viewModel.sections.value.filter {
            it.title.contains(searchText, ignoreCase = true) || it.content.contains(searchText, ignoreCase = true)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text("Поиск") },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            singleLine = true
        )

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            FilterChip(
                selected = !showFavoritesOnly,
                onClick = { showFavoritesOnly = false },
                label = { Text("Все разделы") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = showFavoritesOnly,
                onClick = { showFavoritesOnly = true },
                label = { Text("Закладки") }
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredSections) { section ->
                SectionItem(
                    section = section,
                    isFavorite = viewModel.favorites.value.contains(section.id),
                    onFavoriteClick = { viewModel.toggleFavorite(section.id) },
                    onClick = { viewModel.selectSection(section) }
                )
            }
        }
    }
}

@Composable
fun SectionItem(
    section: TrainingSection,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = section.title,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium
            )
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = if (isFavorite) "Удалить" else "Добавить"
                )
            }
        }
    }
}

@Composable
fun TrainingDetailScreen(section: TrainingSection) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                text = section.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = section.content,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
