package com.example.calorietracker.ui.screens.onboardings.height


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.use_cases.FilterOutDigits
import com.example.calorietracker.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val dataStorePreferences:CalorieTrackerDataStore
):ViewModel() {

    private val _uiEvents = Channel<UIEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    private var _height: MutableState<String> = mutableStateOf("180")
    val height = _height

    fun onHeightChange(height:String){
        if(height.length in 2..3){
            this._height.value = FilterOutDigits().invoke(height)
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            val heightNum = _height.value.toIntOrNull() ?: kotlin.run {
                _uiEvents.send(
                    UIEvents.ShowSnackBar("Height Cannot be null")
                )
                _height.value=""
                return@launch
            }

            dataStorePreferences.saveHeight(heightNum)
        }
    }

}