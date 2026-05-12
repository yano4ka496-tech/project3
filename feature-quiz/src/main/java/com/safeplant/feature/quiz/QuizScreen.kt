package com.safeplant.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel()
) {
    val isQuizStarted by viewModel.isQuizStarted.collectAsState()
    val isFinished by viewModel.isQuizFinished.collectAsState()

    if (!isQuizStarted) {
        StartScreen(
            onStart = { viewModel.startQuiz() }
        )
    } else if (isFinished) {
        val score by viewModel.score.collectAsState()
        val isSuccess by viewModel.isSuccess.collectAsState()
        val total = viewModel.currentQuestions.value.size
        ResultScreen(
            score = score,
            total = total,
            isSuccess = isSuccess,
            onRetry = {
                viewModel.resetQuiz()
            },
            onNavigateToMap = { navController.navigate("map") }
        )
    } else {
        val questions by viewModel.currentQuestions.collectAsState()
        val currentIndex by viewModel.currentQuestionIndex.collectAsState()
        val answers by viewModel.answers.collectAsState()

        if (questions.isNotEmpty()) {
            val question = questions[currentIndex]
            val selectedAnswer = answers[currentIndex] ?: -1
            QuestionScreen(
                question = question,
                currentIndex = currentIndex + 1,
                total = questions.size,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = { answerIndex -> viewModel.selectAnswer(answerIndex) },
                onNext = { viewModel.nextQuestion() },
                onPrevious = { viewModel.previousQuestion() },
                onFinish = { viewModel.finishQuiz() },
                isLast = currentIndex == questions.size - 1
            )
        }
    }
}

@Composable
fun StartScreen(onStart: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Прохождение квиза для посетителей АО «ТАНЕКО» на знание безопасности труда",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Button(
                onClick = onStart,
                modifier = Modifier.width(200.dp)
            ) {
                Text("Начать тест")
            }
        }
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    currentIndex: Int,
    total: Int,
    selectedAnswer: Int,
    onAnswerSelected: (Int) -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onFinish: () -> Unit,
    isLast: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхняя часть – вопрос
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${currentIndex}. ${question.text}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Нижняя часть – кнопки ответов
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttonColors = listOf(
                Color(0xFF2196F3), // синий
                Color(0xFFF44336), // красный
                Color(0xFFFFEB3B)  // жёлтый
            )

            question.options.forEachIndexed { index, option ->
                Button(
                    onClick = { onAnswerSelected(index) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[index % buttonColors.size]
                    )
                ) {
                    Text(
                        text = option,
                        color = if (buttonColors[index % buttonColors.size] == Color(0xFFFFEB3B)) Color.Black else Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onPrevious,
                    enabled = currentIndex > 1
                ) {
                    Text("Назад")
                }

                if (isLast) {
                    Button(
                        onClick = onFinish,
                        enabled = selectedAnswer != -1
                    ) {
                        Text("Завершить")
                    }
                } else {
                    Button(
                        onClick = onNext,
                        enabled = selectedAnswer != -1
                    ) {
                        Text("Далее")
                    }
                }
            }
        }
    }
}

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    isSuccess: Boolean,
    onRetry: () -> Unit,
    onNavigateToMap: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isSuccess) "Поздравляем! Допуск получен" else "К сожалению, вы не прошли",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Правильных ответов: $score из $total",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(onClick = onRetry) {
                Text("Пройти заново")
            }
            Button(onClick = onNavigateToMap) {
                Text("На карту")
            }
        }
    }
}
