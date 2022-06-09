package com.example.calorietracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker.ui.navigation.SetUpNavGraph
import com.example.calorietracker.ui.screens.onboardings.FirstPageOnBoarding
import com.example.calorietracker.ui.screens.onboardings.GenderScreen
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.calorietracker.ui.viewModels.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navViewModel:NavigationViewModel by viewModels()

        setContent {
            CalorieTrackerTheme {
                val screen = navViewModel.startScreen
                val navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    startDestination =screen.value,
                    navViewModel
                )
            }
        }
    }
}

