package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddMealButton(
    name:String,
    color: Color = MaterialTheme.colors.primaryVariant,
    onClick:()->Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable {
                onClick()
            }
            .padding(16.dp)

    ) {
        Text(text = "Add $name",
            style = MaterialTheme.typography.h4,
            color = color
        )

    }



}