package com.example.calorietracker.ui.screens.onboardings


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen

@Composable
fun GenderScreen(
    navController: NavController
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
                onClick = {  },
                isSelected = true,
                selectedTextColor = White,
                color = MaterialTheme.colors.primaryVariant
            )
            
            Spacer(modifier = Modifier.width(16.dp))

            SelectableButton(
                text = "Female",
                onClick = {  },
                isSelected = false,
                selectedTextColor = White,
                color = MaterialTheme.colors.primaryVariant
            )

        }

      

    }
        
        Button(onClick ={navController.navigate(Screen.AgeScreen.route)},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
           Text(text = "Next") 
        }
        
    }
    
}

