package com.example.calorietracker.ui.screens.onboardings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen


@Composable
fun FirstPageOnBoarding(
    navController: NavController,
) {

    Column(
        modifier = Modifier.fillMaxSize().fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "Lets get started!",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(15.dp))
        
        Button(onClick = {navController.navigate(Screen.GenderPage.route)},
            Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(26.dp))){
            Text(text = "Next")
        }
    }

}

