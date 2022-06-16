package com.example.calorietracker.ui.screens.onboardings.activity_level

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.domain.model.ActivityLevel
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.screens.onboardings.SelectableButton

@Composable
fun ActivityLevelScreen(
    navController: NavController,
    viewModel: ActivityLevelViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                style = MaterialTheme.typography.h1,
                text = "What is your activity Level?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            )

            Spacer(modifier = Modifier.height(15.dp))

            Row{
                SelectableButton(
                    text = "Low",
                    onClick = { viewModel.onActivityLevelSelected(ActivityLevel.Low) },
                    isSelected = viewModel.selectedActivityLevel.value is ActivityLevel.Low,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.width(16.dp))

                SelectableButton(
                    text = "Medium",
                    onClick = {  viewModel.onActivityLevelSelected(ActivityLevel.Medium)},
                    isSelected = viewModel.selectedActivityLevel.value is ActivityLevel.Medium,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.width(16.dp))

                SelectableButton(
                    text = "High",
                    onClick = { viewModel.onActivityLevelSelected(ActivityLevel.High) },
                    isSelected = viewModel.selectedActivityLevel.value is ActivityLevel.High,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )


            }



        }

        Button(onClick ={
            viewModel.onNextClick()
            navController.navigate(Screen.NutrientChoiceScreen.route)
                        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }

    }



}

