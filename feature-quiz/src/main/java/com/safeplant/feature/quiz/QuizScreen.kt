package com.safeplant.feature.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel()
) {
    val currentQuestion by viewModel.currentQuestionIndex.collectAsState()
    val answers by viewModel.answers.collectAsState()
    val isFinished by viewModel.isQuizFinished.collectAsState()
    val score by viewModel.score.collectAsState()
    val isSuccess by viewModel.isSuccess.collectAsState()

    if (isFinished) {
        ResultScreen(
            score = score,
            total = viewModel.totalQuestions,
            isSuccess = isSuccess,
            onRetry = { viewModel.resetQuiz() },
            onNavigateToMap = { navController.navigate("map") }
        )
    } else {
        QuestionScreen(
            question = viewModel.currentQuestion,
            currentIndex = currentQuestion + 1,
            total = viewModel.totalQuestions,
            selectedAnswer = answers[currentQuestion],
            onAnswerSelected = { answerIndex -> viewModel.selectAnswer(answerIndex) },
            onNext = { viewModel.nextQuestion() },
            onPrevious = { viewModel.previousQuestion() },
            onFinish = { viewModel.finishQuiz() },
            isLast = currentQuestion == viewModel.totalQuestions - 1
        )
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Вопрос $currentIndex из $total", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = question.text, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            question.options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedAnswer == index,
                        onClick = { onAnswerSelected(index) }
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

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
