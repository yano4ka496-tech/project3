package com.safeplant.feature.training

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Экран закладок
 * @param NavController Навигационный контроллер
 */
@Composable
fun BookmarksScreen(
    navController: NavController,
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    val bookmarks by viewModel.bookmarks.collectAsState()
    val contentItems by viewModel.contentItems.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    // Фильтрация контента по поисковому запросу
    val filteredContent = remember(searchQuery, contentItems) {
        if (searchQuery.isBlank()) {
            contentItems
        } else {
            contentItems.filter { content ->
                content.title.contains(searchQuery, ignoreCase = true) ||
                content.content.contains(searchQuery, ignoreCase = true)
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Закладки") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material3.icons.Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                actions = {
                    androidx.compose.material3.IconButton(
                        onClick = { viewModel.refreshBookmarks() }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material3.icons.Icons.Default.Refresh,
                            contentDescription = "Обновить"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                error != null -> {
                    ContentPlaceholder(
                        onRetry = { viewModel.refreshBookmarks() }
                    )
                }
                filteredContent.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Закладки не найдены",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        
                        Text(
                            text = "У вас пока нет сохраненных закладок",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Поиск
                        var query by remember { mutableStateOf(searchQuery) }
                        
                        TextField(
                            value = query,
                            onValueChange = {
                                query = it
                                viewModel.setSearchQuery(it)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            placeholder = { Text("Поиск по закладкам...") },
                            singleLine = true
                        )
                        
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(filteredContent) { content ->
                                BookmarkItem(
                                    content = content,
                                    onClick = {
                                        navController.navigate(
                                            "${NavigationDestinations.Content.route}/${content.id}"
                                        )
                                    },
                                    onRemove = {
                                        viewModel.removeBookmark(content.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Элемент списка закладок
 */
@Composable
private fun BookmarkItem(
    content: com.safeplant.core.database.entity.TrainingTextContent,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = content.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            androidx.compose.material3.IconButton(
                onClick = onRemove
            ) {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material3.icons.Icons.Default.Delete,
                    contentDescription = "Удалить закладку"
                )
            }
        }
        
        Divider(
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}