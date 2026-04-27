package com.safeplant.feature.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Экран квиза по технике безопасности
 */
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel
) {
    val questions by viewModel.questions.collectAsState()
    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()
    val score by viewModel.score.collectAsState()
    val quizCompleted by viewModel.quizCompleted.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    
    // Получаем текущий вопрос
    val currentQuestion = questions.getOrNull(currentQuestionIndex)
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            // Индикатор загрузки
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (quizCompleted) {
            // Экран результатов
            QuizResultScreen(
                score = score,
                totalQuestions = questions.size,
                onRestart = { viewModel.resetQuiz() },
                navController = navController
            )
        } else if (currentQuestion != null) {
            // Экран вопроса
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Заголовок с прогрессом
                Text(
                    text = "Вопрос ${currentQuestionIndex + 1} из ${questions.size}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                
                // Текст вопроса
                Text(
                    text = currentQuestion.question,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Варианты ответов
                currentQuestion.options.forEach { option ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedAnswer == option) 
                                MaterialTheme.colorScheme.primaryContainer 
                            else 
                                MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.cardBorder(
                            border = if (selectedAnswer == option) 
                                BorderStroke(2.dp, MaterialTheme.colorScheme.primary) 
                            else 
                                CardDefaults.outlinedCardBorder().border
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedAnswer == option,
                                onClick = { viewModel.selectAnswer(option) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                
                // Кнопка "Далее"
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { viewModel.nextQuestion() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedAnswer != null
                ) {
                    Text(
                        text = if (currentQuestionIndex == questions.size - 1) "Завершить" else "Далее",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

/**
 * Экран результатов квиза
 */
@Composable
private fun QuizResultScreen(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Результаты квиза",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        Text(
            text = "Правильных ответов: $score из $totalQuestions",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Text(
            text = if (score >= 8) "Тест пройден!" else "Тест не пройден. Попробуйте снова.",
            fontSize = 18.sp,
            color = if (score >= 8) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onRestart,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Пройти снова", fontSize = 18.sp)
            }
            
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "На карту", fontSize = 18.sp)
            }
        }
    }
}