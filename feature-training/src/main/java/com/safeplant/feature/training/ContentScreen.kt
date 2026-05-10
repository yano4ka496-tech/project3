package com.safeplant.feature.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.media3.ui.PlayerView

/**
 * Экран контента обучающего раздела
 * @param NavController Навигационный контроллер
 * @param contentId Идентификатор контента
 */
@Composable
fun ContentScreen(
    navController: NavController,
    contentId: Long,
    viewModel: ContentViewModel = hiltViewModel()
) {
    val content by viewModel.content.collectAsState()
    val video by viewModel.video.collectAsState()
    val isBookmarked by viewModel.isBookmarked.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    // Загружаем данные при входе на экран
    if (content == null) {
        viewModel.loadContent(contentId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { content?.title ?: "Загрузка..." },
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
                        onClick = { viewModel.toggleBookmark(contentId) }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = if (isBookmarked) 
                                androidx.compose.material3.icons.Icons.Default.Bookmark 
                            else 
                                androidx.compose.material3.icons.Icons.Default.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Удалить из закладок" else "Добавить в закладки"
                        )
                    }
                    
                    androidx.compose.material3.IconButton(
                        onClick = { viewModel.refreshContent(contentId) }
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
                        onRetry = { viewModel.refreshContent(contentId) }
                    )
                }
                content == null -> {
                    ContentPlaceholder(
                        onRetry = { viewModel.refreshContent(contentId) }
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Заголовок
                        Text(
                            text = content.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Divider()
                        
                        // Видео (если есть)
                        video?.let { videoItem ->
                            Text(
                                text = "Видео",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            
                            // Для простоты используем PlayerView как Android View
                            // В реальном приложении нужно использовать Compose VideoPlayer
                            AndroidView(
                                factory = { context ->
                                    PlayerView(context).apply {
                                        // Здесь должна быть инициализация ExoPlayer
                                        // Для примера оставляем заглушку
                                        this.player = null
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                            
                            Divider()
                        }
                        
                        // Текстовый контент
                        Text(
                            text = content.content,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}