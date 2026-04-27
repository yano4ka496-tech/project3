package com.safeplant.feature.quiz

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class QuizSelectionTest {

    @Test
    fun `selectRandomQuestions should return exactly 10 questions`() {
        val allQuestions = listOf(
            QuizQuestion("1", "Вопрос 1", listOf("A", "B", "C"), 0),
            QuizQuestion("2", "Вопрос 2", listOf("A", "B", "C"), 0),
            QuizQuestion("3", "Вопрос 3", listOf("A", "B", "C"), 0),
            QuizQuestion("4", "Вопрос 4", listOf("A", "B", "C"), 0),
            QuizQuestion("5", "Вопрос 5", listOf("A", "B", "C"), 0),
            QuizQuestion("6", "Вопрос 6", listOf("A", "B", "C"), 0),
            QuizQuestion("7", "Вопрос 7", listOf("A", "B", "C"), 0),
            QuizQuestion("8", "Вопрос 8", listOf("A", "B", "C"), 0),
            QuizQuestion("9", "Вопрос 9", listOf("A", "B", "C"), 0),
            QuizQuestion("10", "Вопрос 10", listOf("A", "B", "C"), 0),
            QuizQuestion("11", "Вопрос 11", listOf("A", "B", "C"), 0),
            QuizQuestion("12", "Вопрос 12", listOf("A", "B", "C"), 0)
        )
        
        val selectedQuestions = QuizRepository().selectRandomQuestions(allQuestions)
        assertEquals(10, selectedQuestions.size)
    }

    @Test
    fun `selectRandomQuestions should return unique questions`() {
        val allQuestions = listOf(
            QuizQuestion("1", "Вопрос 1", listOf("A", "B", "C"), 0),
            QuizQuestion("2", "Вопрос 2", listOf("A", "B", "C"), 0),
            QuizQuestion("3", "Вопрос 3", listOf("A", "B", "C"), 0),
            QuizQuestion("4", "Вопрос 4", listOf("A", "B", "C"), 0),
            QuizQuestion("5", "Вопрос 5", listOf("A", "B", "C"), 0),
            QuizQuestion("6", "Вопрос 6", listOf("A", "B", "C"), 0),
            QuizQuestion("7", "Вопрос 7", listOf("A", "B", "C"), 0),
            QuizQuestion("8", "Вопрос 8", listOf("A", "B", "C"), 0),
            QuizQuestion("9", "Вопрос 9", listOf("A", "B", "C"), 0),
            QuizQuestion("10", "Вопрос 10", listOf("A", "B", "C"), 0),
            QuizQuestion("11", "Вопрос 11", listOf("A", "B", "C"), 0),
            QuizQuestion("12", "Вопрос 12", listOf("A", "B", "C"), 0)
        )
        
        val selectedQuestions = QuizRepository().selectRandomQuestions(allQuestions)
        
        // Проверяем, что все вопросы уникальны
        val questionIds = selectedQuestions.map { it.id }
        assertEquals(selectedQuestions.size, questionIds.distinct().size)
    }

    @Test
    fun `selectRandomQuestions should return different sets on multiple calls`() {
        val allQuestions = listOf(
            QuizQuestion("1", "Вопрос 1", listOf("A", "B", "C"), 0),
            QuizQuestion("2", "Вопрос 2", listOf("A", "B", "C"), 0),
            QuizQuestion("3", "Вопрос 3", listOf("A", "B", "C"), 0),
            QuizQuestion("4", "Вопрос 4", listOf("A", "B", "C"), 0),
            QuizQuestion("5", "Вопрос 5", listOf("A", "B", "C"), 0),
            QuizQuestion("6", "Вопрос 6", listOf("A", "B", "C"), 0),
            QuizQuestion("7", "Вопрос 7", listOf("A", "B", "C"), 0),
            QuizQuestion("8", "Вопрос 8", listOf("A", "B", "C"), 0),
            QuizQuestion("9", "Вопрос 9", listOf("A", "B", "C"), 0),
            QuizQuestion("10", "Вопрос 10", listOf("A", "B", "C"), 0),
            QuizQuestion("11", "Вопрос 11", listOf("A", "B", "C"), 0),
            QuizQuestion("12", "Вопрос 12", listOf("A", "B", "C"), 0)
        )
        
        val firstSelection = QuizRepository().selectRandomQuestions(allQuestions)
        val secondSelection = QuizRepository().selectRandomQuestions(allQuestions)
        
        // Проверяем, что выборки разные
        assertNotEquals(firstSelection, secondSelection)
    }

    @Test
    fun `selectRandomQuestions with less than 10 questions should return all questions`() {
        val allQuestions = listOf(
            QuizQuestion("1", "Вопрос 1", listOf("A", "B", "C"), 0),
            QuizQuestion("2", "Вопрос 2", listOf("A", "B", "C"), 0),
            QuizQuestion("3", "Вопрос 3", listOf("A", "B", "C"), 0),
            QuizQuestion("4", "Вопрос 4", listOf("A", "B", "C"), 0),
            QuizQuestion("5", "Вопрос 5", listOf("A", "B", "C"), 0)
        )
        
        val selectedQuestions = QuizRepository().selectRandomQuestions(allQuestions)
        assertEquals(5, selectedQuestions.size)
    }

    @Test
    fun `selectRandomQuestions with empty list should return empty list`() {
        val allQuestions = emptyList<QuizQuestion>()
        
        val selectedQuestions = QuizRepository().selectRandomQuestions(allQuestions)
        assertTrue(selectedQuestions.isEmpty())
    }
}