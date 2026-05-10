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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Экран раздела обучающего контента
 * @param NavController Навигационный контроллер
 * @param sectionId Идентификатор раздела
 */
@Composable
fun SectionScreen(
    navController: NavController,
    sectionId: Long,
    viewModel: SectionViewModel = hiltViewModel()
) {
    val section by viewModel.section.collectAsState()
    val childSections by viewModel.childSections.collectAsState()
    val contentItems by viewModel.contentItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    // Загружаем данные при входе на экран
    if (section == null) {
        viewModel.loadSection(sectionId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { section?.title ?: "Загрузка..." },
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
                        onClick = { viewModel.refreshSection(sectionId) }
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
                        onRetry = { viewModel.refreshSection(sectionId) }
                    )
                }
                section == null -> {
                    ContentPlaceholder(
                        onRetry = { viewModel.refreshSection(sectionId) }
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Дочерние разделы
                        if (childSections.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Подразделы",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                            
                            items(childSections) { childSection ->
                                SectionItem(
                                    section = childSection,
                                    onClick = {
                                        navController.navigate(
                                            "${NavigationDestinations.Section.route}/${childSection.id}"
                                        )
                                    }
                                )
                            }
                        }
                        
                        // Элементы контента
                        if (contentItems.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Контент",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            
                            items(contentItems) { content ->
                                ContentItem(
                                    content = content,
                                    onClick = {
                                        navController.navigate(
                                            "${NavigationDestinations.Content.route}/${content.id}"
                                        )
                                    }
                                )
                            }
                        }
                        
                        if (childSections.isEmpty() && contentItems.isEmpty()) {
                            item {
                                Text(
                                    text = "В этом разделе пока нет контента",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
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
 * Элемент списка контента
 */
@Composable
private fun ContentItem(
    content: com.safeplant.core.database.entity.TrainingTextContent,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = content.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        Divider(
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}