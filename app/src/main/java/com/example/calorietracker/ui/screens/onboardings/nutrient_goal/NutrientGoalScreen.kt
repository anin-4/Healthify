package com.example.calorietracker.ui.screens.onboardings.nutrient_goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.screens.onboardings.UnitTextField
import com.example.calorietracker.utils.UIEvents
import kotlinx.coroutines.flow.collect



@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is UIEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.msg
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "what is your nutrient goal?",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Carbs", style = MaterialTheme.typography.h5)

                Spacer(Modifier.width(10.dp))

                UnitTextField(
                    value = viewModel.nutrient.value.carbRatio,
                    onValueChange = {
                        viewModel.onEnter(NutrientGoalEvent.OnCarbEnter(it))
                    },
                    unit = "%"
                )
            }


            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(text = "Protein", style = MaterialTheme.typography.h5)

                Spacer(Modifier.width(10.dp))

                UnitTextField(
                    value = viewModel.nutrient.value.proteinRatio,
                    onValueChange = {
                        viewModel.onEnter(NutrientGoalEvent.OnProteinEnter(it))
                    },
                    unit ="%"
                )

            }



            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Fat", style = MaterialTheme.typography.h5)

                Spacer(Modifier.width(10.dp))


                UnitTextField(
                    value = viewModel.nutrient.value.fatRatio,
                    onValueChange = {
                        viewModel.onEnter(NutrientGoalEvent.OnFatEnter(it))
                    },
                    unit = "%"
                )
            }


        }
        Button(
            onClick = {
                viewModel.onEnter(NutrientGoalEvent.OnNextClicked)
                if (viewModel.navigate.value)
                    navController.navigate(Screen.WeightChoiceScreen.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }
}