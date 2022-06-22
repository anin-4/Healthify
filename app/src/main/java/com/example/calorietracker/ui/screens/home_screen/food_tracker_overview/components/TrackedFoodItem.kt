package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import com.skydoves.landscapist.glide.GlideImage



@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    modifier:Modifier = Modifier,
    onDeleteButtonClicked:()->Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(10.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .height(80.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        trackedFood.imageUrl?.let{
            GlideImage(
                imageModel = trackedFood.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = " Tracked Food image",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp
                        )
                    )
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = trackedFood.amount.toString()+ "-"+trackedFood.calories.toString(),
                style = MaterialTheme.typography.body1
            )
            
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "delete",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDeleteButtonClicked() }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                NutrientInfo(
                    name = "carbs",
                    amount = trackedFood.carbs,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                NutrientInfo(
                    name = "protein",
                    amount = trackedFood.protein,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                NutrientInfo(
                    name = "Fat",
                    amount = trackedFood.fat,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,

                )
            }

        }



    }

}