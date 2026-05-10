package com.safeplant.feature.training

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.entity.Section
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SectionScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var database: AppDatabase
    private lateinit var sectionDao: SectionDao
    private lateinit var trainingTextContentDao: TrainingTextContentDao

    @Before
    fun setup() {
        val context = composeTestRule.activity.applicationContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        sectionDao = database.sectionDao()
        trainingTextContentDao = database.trainingTextContentDao()

        // Добавляем тестовые данные
        runBlocking {
            val section = sectionDao.insert(Section(title = "Общие правила", order = 1))
            trainingTextContentDao.insert(
                TrainingTextContent(
                    sectionId = section,
                    title = "Использование СИЗ",
                    content = "При работе на производстве необходимо использовать средства индивидуальной защиты.",
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
    fun sectionScreen_displaysSectionTitle() {
        composeTestRule.setContent {
            SectionScreen(
                navController = androidx.navigation.compose.rememberNavController(),
                sectionId = 1
            )
        }

        // Проверяем отображение заголовка раздела
        composeTestRule.onNodeWithText("Общие правила").assertIsDisplayed()
    }

    @Test
    fun sectionScreen_displaysContentItems() {
        composeTestRule.setContent {
            SectionScreen(
                navController = androidx.navigation.compose.rememberNavController(),
                sectionId = 1
            )
        }

        // Проверяем отображение элементов контента
        composeTestRule.onNodeWithText("Использование СИЗ").assertIsDisplayed()
    }

    @Test
    fun sectionScreen_navigatesToContent() {
        var navigatedToContent = false
        composeTestRule.setContent {
            SectionScreen(
                navController = androidx.navigation.compose.NavHostController(composeTestRule.activity).apply {
                    addOnDestinationChangedListener { _, _, _ ->
                        navigatedToContent = true
                    }
                },
                sectionId = 1
            )
        }

        // Нажимаем на элемент контента
        composeTestRule.onNodeWithText("Использование СИЗ").performClick()

        // Проверяем навигацию
        assert(navigatedToContent)
    }
}