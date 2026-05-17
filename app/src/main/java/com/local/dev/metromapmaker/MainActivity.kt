package com.local.dev.metromapmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val stationPositions = remember { mutableStateListOf<Offset>() }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            stationPositions.add(offset)
                        }
                    }
            ) {
                stationPositions.forEach { position ->
                    drawCircle(
                        color = Color.Black,
                        radius = 16f,
                        center = position
                    )
                }
            }
        }
    }
}
