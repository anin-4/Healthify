package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.Meal

@Composable
fun ExpandableMealItem(
    modifier:Modifier = Modifier,
    comp : @Composable ()->Unit,
    onToggleClick:()->Unit,
    meal: Meal
) {

    Column(
        modifier = modifier
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onToggleClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = painterResource(id = meal.drawableRes), contentDescription = meal.name)

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = meal.name,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary
                    )

                    IconButton(onClick = { onToggleClick()}) {
                        Icon(imageVector = if (meal.toggleExpand) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                            contentDescription = "toggle button"
                        )
                    }



                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    UnitDisplay(
                        amount = meal.calories,
                        unit = "kcal",
                        amountTextSize = 30.sp
                    )

                    Row {
                        NutrientInfo(
                            name = "Carbs",
                            amount = meal.carbs,
                            unitText = "g"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        NutrientInfo(
                            name = "Protein",
                            amount = meal.protein,
                            unitText = "g"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        NutrientInfo(
                            name = "Fat",
                            amount = meal.fat,
                            unitText = "g"
                        )
                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(visible = meal.toggleExpand){
            comp()
        }

    }

}