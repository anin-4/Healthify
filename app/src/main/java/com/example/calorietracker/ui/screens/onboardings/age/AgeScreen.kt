package com.example.calorietracker.ui.screens.onboardings.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.screens.onboardings.UnitTextField

@Composable
fun AgeScreen(
    navController: NavController,
    viewModel: AgeViewModel = hiltViewModel()

) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            Text(text = "What is your age?",
                style = MaterialTheme.typography.h1,
                
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            
            UnitTextField(
                value = viewModel.age.value,
                onValueChange = {
                    viewModel.onAgeChange(it)
                },
                unit = "yrs" )



        }
        Button(
            onClick ={
            viewModel.saveToPreference()
            navController.navigate(Screen.HeightScreen.route)
                        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }

}
