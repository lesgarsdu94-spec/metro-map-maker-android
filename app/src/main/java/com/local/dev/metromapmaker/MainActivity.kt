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

// v0.0.2 Requirement: Custom Data Structure for Stations
data class MetroStation(
    val id: Int,
    val coordinate: Offset,
    val name: String // Holds the auto-generated name
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State-driven array holding our structured data objects
            val metroStations = remember { mutableStateListOf<MetroStation>() }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)) // Light canvas matching baseline
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            // v0.0.2 Requirement: Auto-naming engine logic
                            val nextId = metroStations.size + 1
                            val newStation = MetroStation(
                                id = nextId,
                                coordinate = offset,
                                name = "Station $nextId" // Automatically names them sequentially
                            )
                            metroStations.add(newStation)
                        }
                    }
            ) {
                // Renders the stations from the data structure
                metroStations.forEach { station ->
                    drawCircle(
                        color = Color.Black,
                        radius = 16f,
                        center = station.coordinate
                    )
                }
            }
        }
    }
}
