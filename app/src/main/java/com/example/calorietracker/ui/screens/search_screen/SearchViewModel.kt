package com.example.calorietracker.ui.screens.search_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.domain.food_tracker.use_cases.TrackFoodUseCases
import com.example.calorietracker.utils.Resource
import com.example.calorietracker.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
   private val useCases: TrackFoodUseCases
):ViewModel() {



    var state:MutableState<SearchScreenState> = mutableStateOf(SearchScreenState())
    private set

    private val _uiEvents:Channel<UIEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun onEvent(event:SearchScreenEvents){
        when(event){
            is SearchScreenEvents.OnQueryChange -> state.value= state.value.copy( searchQuery = event.query)

            is SearchScreenEvents.OnAmountChangeForFood -> state.value = state.value.copy(
                trackableFood = state.value.trackableFood.map {
                    if(it.food == event.food){
                        it.copy(
                            amount = filterOutDigits(event.amount)
                        )
                    }else it
                }
            )

            is SearchScreenEvents.OnSearch -> onSearch()

            is SearchScreenEvents.OnToggleTrackableFood -> {
                state.value = state.value.copy(
                    trackableFood = state.value.trackableFood.map {
                        if(it.food == event.food){
                            it.copy(isExpanded = !it.isExpanded)
                        }else it
                    }
                )
            }

            is SearchScreenEvents.OnSearchFocusChange -> {
                state.value = state.value.copy(
                    isHintVisible = !event.isFocused && state.value.searchQuery.isBlank()
                )
            }

            is SearchScreenEvents.OnTrackFoodClicked ->{
                trackFood(event)
            }
        }
    }

    private fun trackFood(event: SearchScreenEvents.OnTrackFoodClicked) {
        viewModelScope.launch {
            val item = state.value.trackableFood.find {
                it.food == event.food
            }

            useCases.trackFood(
                food = item?.food ?:return@launch,
                amount = item.amount.toIntOrNull()?:return@launch,
                mealType = event.mealTypes,
                date = event.date
            )

            //navigate up after that
        }

    }

    private fun onSearch() {
        viewModelScope.launch {
            state.value = state.value.copy(
                isLoading = true,
                trackableFood = emptyList()
            )

            val result = useCases.searchFood(
                query = state.value.searchQuery,
                pageSize = 10,
                page = 1
            )

            when(result){
                is Resource.Success -> {
                    state.value = state.value.copy(
                        searchQuery = "",
                        isLoading = false,
                        trackableFood = result.data?.map {
                            TrackableFoodState(food = it)
                        }?: emptyList()

                    )
                }

                is Resource.Error ->{
                    state.value = state.value.copy(isLoading = false)
                    _uiEvents.send(
                        UIEvents.ShowSnackBar(
                            "An Error occurred"
                        )
                    )
                }
            }
        }
    }

    private fun filterOutDigits(text:String):String{
        return text.filter { it.isDigit() }
    }


}