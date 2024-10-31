package com.example.chewsyui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chewsyui.ui.theme.ChewsyUITheme
import com.example.chewsyui.ui.theme.cobaltBlue


class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            CalorieTrackingScreen()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalorieTrackingScreen()
}

@Composable
fun CalorieProgressWheel(
    calorieCount: Float,
    maxCalories: Float
) {
    val progress = calorieCount / maxCalories
    val strokeWidth = 30f // Stroke width for the wheel

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(300.dp)
    ) {
        // Draw the background circle
        Canvas(modifier = Modifier.size(240.dp)) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            // Draw the progress arc
            drawArc(
                color = cobaltBlue,
                startAngle = 270f, // Start from the top
                sweepAngle = progress * 360f,
                useCenter = false,
                style = Stroke(strokeWidth)
            )
        }

        // Display calorie count in the center of the wheel
        Text(
            text = "${calorieCount.toInt()} / ${maxCalories.toInt()} cal",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MealItem(mealName: String, mealCalories: Float) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = mealName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${mealCalories.toInt()} cal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun CalorieTrackingScreen() {
    var calorieCount by remember { mutableStateOf(0f) } // Default calorie count
    val maxCalories = 2300f // Maximum calories for the wheel
    val progress = calorieCount / maxCalories
    var mealName by remember { mutableStateOf("") }
    var mealCalories by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Select Meal") }

    val breakFastList = remember { mutableStateListOf<Pair<String, Float>>() }
    val lunchList = remember { mutableStateListOf<Pair<String, Float>>() }
    val dinnerList = remember { mutableStateListOf<Pair<String, Float>>() }
    val snacksList = remember { mutableStateListOf<Pair<String, Float>>() }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Title
        item {
            Text(
                text = "Chewsy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = cobaltBlue
            )
        }

        // Circular Progress Wheel for Calories
        item {
            CalorieProgressWheel(
                calorieCount = calorieCount,
                maxCalories = maxCalories
            )
        }
        item {
            OutlinedTextField(
                value = mealName,
                onValueChange = { mealName = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
        item {
            OutlinedTextField(
                value = mealCalories,
                onValueChange = { mealCalories = it },
                label = { Text("Calories") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
        item {
            var expanded by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(cobaltBlue),
                    onClick = { expanded = true }) {
                    Text(selectedCategory)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Breakfast", "Lunch", "Dinner", "Snacks").forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        item {
            Button(
                colors = ButtonDefaults.buttonColors(cobaltBlue),
                onClick = {

                    val calories = mealCalories.toFloatOrNull()
                    if (mealName.isNotEmpty() && calories != null) {
                        when (selectedCategory) {
                            "Breakfast" -> breakFastList.add(mealName to calories)
                            "Lunch" -> lunchList.add(mealName to calories)
                            "Dinner" -> dinnerList.add(mealName to calories)
                            "Snacks" -> snacksList.add(mealName to calories)
                        }
                        calorieCount += calories
                        mealName = ""
                        mealCalories = ""
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Add Meal")
            }
        }
        // List of Meals
        item { CategorySection("Breakfast", breakFastList) }
        item { CategorySection("Lunch", lunchList) }
        item { CategorySection("Dinner", dinnerList) }
        item { CategorySection("Snacks", snacksList) }
    }
}






        @Composable
        fun CategorySection(categoryName: String, mealList: List<Pair<String, Float>>) {
            if (mealList.isNotEmpty()) {
                Text(
                    text = categoryName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                mealList.forEach { meal ->
                    MealItem(mealName = meal.first, mealCalories = meal.second)
                }
            }
        }


