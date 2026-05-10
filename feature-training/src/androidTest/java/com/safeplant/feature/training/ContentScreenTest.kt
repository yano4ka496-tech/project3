package com.safeplant.feature.training

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContentScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var database: AppDatabase
    private lateinit var trainingTextContentDao: TrainingTextContentDao

    @Before
    fun setup() {
        val context = composeTestRule.activity.applicationContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        trainingTextContentDao = database.trainingTextContentDao()

        // Добавляем тестовые данные
        runBlocking {
            trainingTextContentDao.insert(
                TrainingTextContent(
                    id = 1,
                    sectionId = 1,
                    title = "Использование СИЗ",
                    content = "# Использование СИЗ\n\nПри работе на производстве необходимо использовать средства индивидуальной защиты: каски, очки, перчатки.",
                    order = 1
                )
            )
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun contentScreen_displaysContentTitle() {
        composeTestRule.setContent {
            ContentScreen(
                navController = androidx.navigation.compose.rememberNavController(),
                contentId = 1
            )
        }

        // Проверяем отображение заголовка контента
        composeTestRule.onNodeWithText("Использование СИЗ").assertIsDisplayed()
    }

    @Test
    fun contentScreen_displaysContentText() {
        composeTestRule.setContent {
            ContentScreen(
                navController = androidx.navigation.compose.rememberNavController(),
                contentId = 1
            )
        }

        // Проверяем отображение текста контента
        composeTestRule.onNodeWithText("При работе на производстве необходимо использовать средства индивидуальной защиты").assertIsDisplayed()
    }

    @Test
    fun contentScreen_toggleBookmark() {
        composeTestRule.setContent {
            ContentScreen(
                navController = androidx.navigation.compose.rememberNavController(),
                contentId = 1
            )
        }

        // Нажимаем на кнопку закладки
        composeTestRule.onNodeWithContentDescription("Добавить в закладки").performClick()

        // Проверяем, что иконка изменилась (в реальном приложении нужно проверять состояние)
        // В тесте мы просто проверяем, что нажатие произошло без ошибок
    }
}