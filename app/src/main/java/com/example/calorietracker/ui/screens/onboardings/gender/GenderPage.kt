package com.example.calorietracker.ui.screens.onboardings.gender


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.domain.on_boarding.model.Gender
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.screens.onboardings.SelectableButton

@Composable
fun GenderScreen(
    navController: NavController,
    viewModel: GenderViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            style = MaterialTheme.typography.h1,
            text = "What is your gender?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)

        )
        
        Spacer(modifier = Modifier.height(15.dp))

        Row{
            SelectableButton(
                text = "Male",
                onClick = { viewModel.onGenderClick(Gender.Male)  },
                isSelected = viewModel.selectedGender.value is Gender.Male,
                selectedTextColor = White,
                color = MaterialTheme.colors.primaryVariant
            )
            
            Spacer(modifier = Modifier.width(16.dp))

            SelectableButton(
                text = "Female",
                onClick = { viewModel.onGenderClick(Gender.Female)},
                isSelected = viewModel.selectedGender.value is Gender.Female,
                selectedTextColor = White,
                color = MaterialTheme.colors.primaryVariant
            )

        }

    }
        
        Button(
            onClick ={
            viewModel.saveToPreference()
            navController.navigate(Screen.AgeScreen.route)
                     },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
           Text(text = "Next") 
        }
        
    }
    
}

