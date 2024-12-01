package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.HomeState
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _homeState = mutableStateOf<HomeState>(HomeState.Loading)
    val homeState: MutableState<HomeState> = _homeState

    init {
        viewModelScope.launch {
            _homeState.value = HomeState.Success("India")
        }
    }
}