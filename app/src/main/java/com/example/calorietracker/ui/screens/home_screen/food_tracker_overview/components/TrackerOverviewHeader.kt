package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.TrackScreenState

@Composable
fun Header(
    state:TrackScreenState,
    modifier: Modifier=Modifier
){
    val animatedCalorieCount = remember{
        Animatable(0f)
    }
    
    val animatedTotalCalorieCount = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = state.currentCalorieCount){
        animatedCalorieCount.animateTo(state.currentCalorieCount.toFloat())

    }
    
    LaunchedEffect(key1 = state.calorieGoal){
        animatedTotalCalorieCount.animateTo(state.calorieGoal.toFloat())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 50.dp,
                    bottomStart = 50.dp
                )
            )
            .background(color = MaterialTheme.colors.primary)
            .padding(horizontal = 30.dp, vertical = 30.dp)

    ) {
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value.toInt(),
                unit = "kcal",
                amountColor = MaterialTheme.colors.onPrimary,
                unitColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                modifier = Modifier.padding(top = 14.dp)

            )
            
            Column{
                Text(
                    text = "Your Goal",
                    fontSize = 14.sp,
                    color= MaterialTheme.colors.onPrimary,
                )

                UnitDisplay(
                    amount = animatedTotalCalorieCount.value.toInt(),
                    unit = "Kcal",
                    amountColor = MaterialTheme.colors.onPrimary,
                    unitColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,

                )
                
            }

        }
        
        Spacer(modifier = Modifier.height(14.dp))

        NutrientsBar(
            carbs = state.currentCarbCount,
            protein = state.currentProteinCount,
            fat = state.currentFatCount,
            calories =state.currentCalorieCount ,
            caloriesGoal = state.calorieGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        
        Spacer(modifier = modifier.height(14.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            NutrientBarInfo(
                goal = state.carbGoal,
                value = state.currentCarbCount,
                name = "Carbs",
                color = Color.Yellow,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                goal = state.proteinGoal,
                value = state.currentProteinCount,
                name = "Protein",
                color = Color.Magenta,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                goal = state.fatGoal,
                value = state.currentFatCount,
                name = "Fat",
                color = Color.Red,
                modifier = Modifier.size(90.dp)
            )

        }

    }
}

