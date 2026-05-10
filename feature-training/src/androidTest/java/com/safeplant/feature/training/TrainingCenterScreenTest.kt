package com.safeplant.feature.training

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.entity.Section
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrainingCenterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var database: AppDatabase
    private lateinit var sectionDao: SectionDao

    @Before
    fun setup() {
        val context = composeTestRule.activity.applicationContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        sectionDao = database.sectionDao()

        // Добавляем тестовые данные
        runBlocking {
            sectionDao.insert(Section(title = "Общие правила", order = 1))
            sectionDao.insert(Section(title = "Чрезвычайные ситуации", order = 2))
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun trainingCenterScreen_displaysSections() {
        composeTestRule.setContent {
            TrainingCenterScreen(
                onNavigateToSection = { /* NOP */ },
                onNavigateToBookmarks = { /* NOP */ }
            )
        }

        // Проверяем отображение разделов
        composeTestRule.onNodeWithText("Общие правила").assertIsDisplayed()
        composeTestRule.onNodeWithText("Чрезвычайные ситуации").assertIsDisplayed()
    }

    @Test
    fun trainingCenterScreen_navigatesToSection() {
        var navigatedToSection = false
        composeTestRule.setContent {
            TrainingCenterScreen(
                onNavigateToSection = { navigatedToSection = true },
                onNavigateToBookmarks = { /* NOP */ }
            )
        }

        // Нажимаем на раздел
        composeTestRule.onNodeWithText("Общие правила").performClick()

        // Проверяем навигацию
        assert(navigatedToSection)
    }

    @Test
    fun trainingCenterScreen_navigatesToBookmarks() {
        var navigatedToBookmarks = false
        composeTestRule.setContent {
            TrainingCenterScreen(
                onNavigateToSection = { /* NOP */ },
                onNavigateToBookmarks = { navigatedToBookmarks = true }
            )
        }

        // Нажимаем на кнопку закладок
        composeTestRule.onNodeWithText("Закладки").performClick()

        // Проверяем навигацию
        assert(navigatedToBookmarks)
    }
}