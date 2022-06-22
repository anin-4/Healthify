package com.example.calorietracker.ui.screens.search_screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.domain.food_tracker.model.MealType
import com.example.calorietracker.ui.screens.search_screen.components.SearchTextField
import com.example.calorietracker.ui.screens.search_screen.components.TrackableFoodItem
import com.example.calorietracker.utils.UIEvents
import kotlinx.coroutines.flow.collect
import java.time.LocalDate

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    mealType:String,
    day:Int,
    month:Int,
    year:Int,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect{
            when (it) {
                is UIEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        it.msg
                    )
                    keyboardController?.hide()

                }
                else -> Unit
            }
        }
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = mealType,
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchTextField(
                text = viewModel.state.value.searchQuery,
                onValueChange = {
                    viewModel.onEvent(SearchScreenEvents.OnQueryChange(it))
                },
                onSearch = {
                    viewModel.onEvent(SearchScreenEvents.OnSearch)
                },
                onFocusChange = {
                    viewModel.onEvent(SearchScreenEvents.OnSearchFocusChange(it.isFocused))
                },
                )

            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.state.value.trackableFood) { food ->
                    TrackableFoodItem(
                        trackableFoodState = food,
                        onClick = {
                            viewModel.onEvent(SearchScreenEvents.OnToggleTrackableFood(food.food))
                        },
                        onAmountChange = {
                            viewModel.onEvent(SearchScreenEvents.OnAmountChangeForFood(
                                food.food, it
                            ))
                        },
                        onTrack = {
                            viewModel.onEvent(
                                SearchScreenEvents.OnTrackFoodClicked(
                                    food = food.food,
                                    mealTypes = MealType.fromString(mealType),
                                    date = LocalDate.of(year, month, day)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        onNavigate = {navController.navigateUp()}
                    )
                }
            }
        }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewModel.state.value.isLoading -> CircularProgressIndicator()
            viewModel.state.value.trackableFood.isEmpty() -> {
                Text(
                    text = "No results",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
        }
    }


