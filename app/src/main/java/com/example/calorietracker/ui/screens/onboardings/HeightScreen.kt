package com.example.calorietracker.ui.screens.onboardings

import android.view.Display
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen

@Composable
fun HeightScreen(
        navController: NavController
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

            Text(text = "What is your height?",
                style = MaterialTheme.typography.h1,

                )

            Spacer(modifier = Modifier.height(12.dp))


            UnitTextField(value = "180", onValueChange = {} , unit = "cm" )



        }
        Button(onClick ={navController.navigate(Screen.ActivityLevelScreen.route)},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }

}

