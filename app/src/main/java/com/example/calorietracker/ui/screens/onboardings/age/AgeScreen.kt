package com.example.calorietracker.ui.screens.onboardings.age

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
fun AgeScreen(
    navController: NavController,
    viewModel: AgeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState

) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is UIEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.msg
                    )
                }
                else -> Unit
            }
        }
    }

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
                if(viewModel.age.value!="")
            navController.navigate(Screen.WeightScreen.route)
                        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }

}
