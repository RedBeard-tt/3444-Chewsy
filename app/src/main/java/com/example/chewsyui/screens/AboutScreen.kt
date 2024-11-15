package com.example.chewsyui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chewsyui.CustomAppBar


@Composable
fun AboutScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = { CustomAppBar(drawerState = drawerState, title = "About Us") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .padding(16.dp), // Add padding inside scroll area
            horizontalAlignment = Alignment.Start, // Align text to the start
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "About Chewsy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Welcome to Chewsy, your personalized health and weight management companion! Our mission is to help you take control of your health and achieve your goals with practical tools and insights.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Key Features:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Personalized Goal Setting\nEnter your health information, and we’ll guide you in setting achievable weight management goals.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Calorie Wheel\nStay on track with our intuitive Calorie Wheel, displayed prominently on the homepage. It shows the calories you've consumed each day, helping you make informed choices at a glance.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Recipe Book\nStore your favorite healthy recipes in one convenient place. Whether you’re meal-prepping or looking for inspiration, your Recipe Book will always be there for you.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Progress Calendar\nTrack your journey with our Progress Calendar. See which days you’ve met your goals and celebrate your consistency.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Why Choose Chewsy?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            Text(
                text = "We’re here to empower you with the tools and motivation to live a healthier life. Whether you're starting fresh or maintaining progress, Chewsy is your partner every step of the way.",
                fontSize = 16.sp
            )
        }
    }
}