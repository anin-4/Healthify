package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calorietracker.utils.parseDateText
import java.time.LocalDate

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    date:LocalDate,
    onNextDayClicked:()->Unit,
    onPrevDayClicked:()->Unit,

) {
    
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        IconButton(onClick = onPrevDayClicked) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back arrow")
            
        }

        Text(
            text = parseDateText(date),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h5
        )

        IconButton(onClick = onNextDayClicked) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "front arrow")

        }
        
        
    }

    
    
}