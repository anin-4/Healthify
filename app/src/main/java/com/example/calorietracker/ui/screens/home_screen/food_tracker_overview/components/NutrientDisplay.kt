package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NutrientInfo(
    modifier:Modifier= Modifier,
    amount:Int,
    unitText:String,
    name:String,
    unitTextSize: TextUnit = 14.sp,
    amountTextSize:TextUnit = 20.sp
    ) {
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UnitDisplay(
            amount = amount,
            unit = unitText,
            amountTextSize = amountTextSize,
            unitTextSize = unitTextSize
        )

        Spacer(modifier.height(6.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Light
        )


    }



}