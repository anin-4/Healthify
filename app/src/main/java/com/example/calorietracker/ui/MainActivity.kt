package com.example.calorietracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker.ui.navigation.SetUpNavGraph
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.calorietracker.ui.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navViewModel: NavigationViewModel by viewModels()

        setContent {
            CalorieTrackerTheme {
                val screen = navViewModel.startScreen
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    SetUpNavGraph(
                        navController = navController,
                        startDestination =screen.value,
                        scaffoldState = scaffoldState
                    )
                }

            }
        }
    }
}

