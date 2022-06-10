package com.example.calorietracker.ui.screens.onboardings.gender

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.model.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenderViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    var selectedGender: MutableState<Gender> = mutableStateOf(Gender.Male)
    private set

    fun onGenderClick(gender:Gender){
        selectedGender.value = gender
    }

    fun saveToPreference(){
        viewModelScope.launch {
            dataStore.saveGender(selectedGender.value)
        }

    }
}
