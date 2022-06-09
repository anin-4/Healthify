package com.example.calorietracker.ui.screens.onboardings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.viewModels.NavigationViewModel

@Composable
fun WeightChoiceScreen(
    navController: NavController,
    navViewModel :NavigationViewModel
) {

    Box(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                style = MaterialTheme.typography.h1,
                text = "What do you want to do with your current weight? ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            )

            Spacer(modifier = Modifier.height(15.dp))

            Row{
                SelectableButton(
                    text = "Lose",
                    onClick = {  },
                    isSelected = true,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.width(16.dp))

                SelectableButton(
                    text = "Keep",
                    onClick = {  },
                    isSelected = true,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.width(16.dp))

                SelectableButton(
                    text = "Gain",
                    onClick = {  },
                    isSelected = false,
                    selectedTextColor = Color.White,
                    color = MaterialTheme.colors.primaryVariant
                )


            }



        }

        Button(onClick ={
                        navViewModel.saveOnBoardingState(true)
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)

        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }

    }



}
