package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.foundation.Canvas
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color


@Composable
fun NutrientsBar(
    carbs:Int,
    protein:Int,
    fat:Int,
    calories:Int,
    caloriesGoal:Int,
    modifier:Modifier = Modifier
) {

    val carbWidthRatio = remember{
        Animatable(0f)
    }

    val proteinWidthRatio = remember{
        Animatable(0f)
    }

    val fatWidthRatio = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / caloriesGoal)
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / caloriesGoal)
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / caloriesGoal)
        )
    }

    Canvas(modifier = modifier){
        if(calories <= caloriesGoal) {
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width
            drawRoundRect(
                color = Color.White,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = Color.Red,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = Color.Magenta,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = Color.Yellow,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        } else {
            drawRoundRect(
                color = Color.Green,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }

    
}