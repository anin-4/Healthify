package com.example.calorietracker.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val dataStoreRepository:CalorieTrackerDataStore
):ViewModel() {


    private var _startScreen:MutableState<String> = mutableStateOf(Screen.FirstPage.route)
    val startScreen: State<String> = _startScreen

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->

                if (completed) {
                    _startScreen.value = Screen.Home.route
                } else {
                    _startScreen.value = Screen.FirstPage.route
                }
            }
        }
    }

    fun saveOnBoardingState(completed:Boolean) {
        viewModelScope.launch {
            dataStoreRepository.saveOnBoardingState(completed)
        }
    }



}