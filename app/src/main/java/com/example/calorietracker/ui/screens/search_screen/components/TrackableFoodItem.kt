package com.example.calorietracker.ui.screens.search_screen.components

import com.example.calorietracker.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.NutrientInfo
import com.example.calorietracker.ui.screens.search_screen.TrackableFoodState
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun TrackableFoodItem(
    trackableFoodState: TrackableFoodState,
    onClick:()->Unit,
    onTrack:()->Unit,
    onAmountChange:(String)->Unit,
    modifier: Modifier = Modifier,
    onNavigate:()->Unit
) {
    val trackableFood = trackableFoodState.food

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(4.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                trackableFood.imageUrl?.let {
                    GlideImage(
                        imageModel = trackableFood.imageUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = " Tracked Food image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(topStart = 5.dp))
                    )
                }


                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Text(
                        text = trackableFood.name,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = trackableFood.caloriesPer100g.toString() + "kcal" + " / 100g",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Row {
                NutrientInfo(
                    name = "carbs",
                    amount = trackableFood.carbsPer100g,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,

                )
                Spacer(modifier = Modifier.width(5.dp))
                NutrientInfo(
                    name = "protein",
                    amount = trackableFood.proteinPer100g,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,

                )
                Spacer(modifier = Modifier.width(5.dp))
                NutrientInfo(
                    name = "fat",
                    amount = trackableFood.fatPer100g,
                    unitText = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                )
            }
        }
        AnimatedVisibility(visible = trackableFoodState.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Row {
                    BasicTextField(
                        value = trackableFoodState.amount,
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if(trackableFoodState.amount.isNotBlank()) {
                                ImeAction.Done
                            } else ImeAction.Default
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onTrack()
                                defaultKeyboardAction(ImeAction.Done)
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .border(
                                shape = RoundedCornerShape(5.dp),
                                width = 0.5.dp,
                                color = MaterialTheme.colors.onSurface
                            )
                            .alignBy(LastBaseline)
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "g",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
                IconButton(
                    onClick = {
                        onTrack()
                        onNavigate()

                              },
                    enabled = trackableFoodState.amount.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "track"
                    )
                }
            }
        }
    }
    
}