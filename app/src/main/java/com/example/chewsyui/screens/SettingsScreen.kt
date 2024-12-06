package com.example.chewsyui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.chewsyui.CustomAppBar
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(drawerState: DrawerState) {

    val context = LocalContext.current

    // State variables to hold user information
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("4'7\"") }
    var gender by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("") }

    // Expanded state for dropdowns
    var heightExpanded by remember { mutableStateOf(false) }
    var genderExpanded by remember { mutableStateOf(false) }
    var activityLevelExpanded by remember { mutableStateOf(false) }

    // Generate height options from 4'7" to 7'5"
    val heightOptions = buildList {
        for (feet in 4..7) {
            for (inches in 0..11) {
                if (feet == 4 && inches < 7) continue
                if (feet == 7 && inches > 5) break
                add("${feet}'${inches}\"")
            }
        }
    }

    // List of activity levels
    val activityLevels = listOf(
        "Sedentary (No Exercise)",
        "Light Exercise (1-2 days/week)",
        "Moderate Exercise (3-4 days/week)",
        "Heavy Exercise (5-6 days/week)"
    )

    // List of genders
    val genders = listOf("Male", "Female", "Other")

    Scaffold(
        topBar = { CustomAppBar(drawerState = drawerState, title = "Settings") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Age Input
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Weight Input (in pounds)
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (lbs)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Height Dropdown
            ExposedDropdownMenuBox(
                expanded = heightExpanded,
                onExpandedChange = { heightExpanded = !heightExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // TextField that shows the selected height
                OutlinedTextField(
                    value = height,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Height") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = heightExpanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                // Dropdown menu with height options
                ExposedDropdownMenu(
                    expanded = heightExpanded,
                    onDismissRequest = { heightExpanded = false }
                ) {
                    heightOptions.forEach { heightOption ->
                        DropdownMenuItem(
                            text = { Text(heightOption) },
                            onClick = {
                                height = heightOption
                                heightExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            // Gender Dropdown
            ExposedDropdownMenuBox(
                expanded = genderExpanded,
                onExpandedChange = { genderExpanded = !genderExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // TextField that shows the selected gender
                OutlinedTextField(
                    value = gender.ifEmpty { "Select Gender" },
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Gender") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = genderExpanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                // Dropdown menu with gender options
                ExposedDropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false }
                ) {
                    genders.forEach { genderOption ->
                        DropdownMenuItem(
                            text = { Text(genderOption) },
                            onClick = {
                                gender = genderOption
                                genderExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            // Activity Level Dropdown
            ExposedDropdownMenuBox(
                expanded = activityLevelExpanded,
                onExpandedChange = { activityLevelExpanded = !activityLevelExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // TextField that shows the selected activity level
                OutlinedTextField(
                    value = activityLevel.ifEmpty { "Select Activity Level" },
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Activity Level") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = activityLevelExpanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                // Dropdown menu with activity level options
                ExposedDropdownMenu(
                    expanded = activityLevelExpanded,
                    onDismissRequest = { activityLevelExpanded = false }
                ) {
                    activityLevels.forEach { level ->
                        DropdownMenuItem(
                            text = { Text(level) },
                            onClick = {
                                activityLevel = level
                                activityLevelExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            // Submit Button
            Button(
                onClick = {
                    println("Submitted: Age=$age, Weight=$weight, Height=$height, Gender=$gender, Activity=$activityLevel")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Save Information")
            }
        }
    }
}