package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.SummaryViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface SummaryState{
    data object Loading: SummaryState
    data class Success(val summary: String): SummaryState
    data class Error(val message: String): SummaryState
}

@Composable
fun SummaryScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<SummaryViewModel>()
    val state = viewModel.summaryState.value
    SummaryScreenContent(modifier = modifier, state = state)
}


@Composable
fun SummaryScreenContent(modifier: Modifier = Modifier, state: SummaryState) {
    Text(text = "Home Screen", modifier = modifier)
}