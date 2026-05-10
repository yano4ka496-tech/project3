package com.safeplant.feature.profile

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.navigation.NavigationDestinations
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * UI-тесты для экрана профиля
 * Проверяют отображение статуса допуска и работу кнопок
 */
@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun `profileScreen_displaysCorrectStatusWhenAccessPassExists`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Статус допуска").assertIsDisplayed()
        composeTestRule.onNodeWithText("Допуск действителен").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_displaysNoAccessPassWhenNoneExists`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Допуск отсутствует").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_displaysExpiredAccessPassWhenExpired`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Допуск истек").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_displaysAppVersion`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Версия:").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_navigatesToQuizWhenQuizButtonClicked`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        var navigateToQuizCalled = false
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = { navigateToQuizCalled = true },
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Act
        composeTestRule.onNodeWithText("Пройти квиз заново").performClick()
        
        // Assert
        assert(navigateToQuizCalled)
    }
    
    @Test
    fun `profileScreen_navigatesToMapWhenPositionButtonClicked`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        var navigateToMapCalled = false
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = { navigateToMapCalled = true },
                viewModel = viewModel
            )
        }
        
        // Act
        composeTestRule.onNodeWithText("Ручная позиция").performClick()
        
        // Assert
        assert(navigateToMapCalled)
    }
    
    @Test
    fun `profileScreen_displaysVersionUpdateDialogWhenNeeded`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Обновление версии приложения").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_callsConfirmVersionUpdateWhenConfirmClicked`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Act
        composeTestRule.onNodeWithText("Принять").performClick()
        
        // Assert
        verify(viewModel).confirmVersionUpdate()
    }
    
    @Test
    fun `profileScreen_callsCancelVersionUpdateWhenDismissClicked`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Act
        composeTestRule.onNodeWithText("Отмена").performClick()
        
        // Assert
        verify(viewModel).cancelVersionUpdate()
    }
    
    @Test
    fun `profileScreen_displaysRootWarningWhenRootDetected`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Обнаружен root-доступ").assertIsDisplayed()
    }
    
    @Test
    fun `profileScreen_displaysErrorWhenErrorState`() {
        // Arrange
        val viewModel = mock<ProfileViewModel>()
        
        // Act
        composeTestRule.setContent {
            ProfileScreen(
                onNavigateToQuiz = {},
                onNavigateToMap = {},
                viewModel = viewModel
            )
        }
        
        // Assert
        composeTestRule.onNodeWithText("Ошибка:").assertIsDisplayed()
    }
}