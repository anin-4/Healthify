package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NutrientBarInfo(
    goal:Int,
    value:Int,
    name:String,
    color: Color,
    modifier:Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {

    val completionColor = Color.Green

    val angleRatio = remember{
        Animatable(0f)
    }
    
    LaunchedEffect(key1 = value){
        angleRatio.animateTo(
            targetValue =
            if (goal>0) (value/goal).toFloat()
            else 0f,
            animationSpec = tween(
                durationMillis = 200
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)){

            drawArc(
                color = if(value>=goal) completionColor else Color.White,
                startAngle = 0f,
                sweepAngle = 360f,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                ),
                useCenter = false
            )


            if(value<goal){
                drawArc(
                    color = color,
                    useCenter = false,
                    startAngle = 90f,
                    sweepAngle = angleRatio.value*360f,
                    style= Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    ),
                    size = size
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UnitDisplay(
                amount = value,
                unit = "g",
                amountColor = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else completionColor,
                unitColor = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else completionColor
            )
            Text(
                text = name,
                color = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else completionColor,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayNutrientBarInfo() {
    NutrientBarInfo(goal = 50, value = 24, name = "carbs", color = Color.Yellow)
}