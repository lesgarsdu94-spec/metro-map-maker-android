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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput

// New Data Structure representing an actual transit station
data class MetroStation(
    val id: Int,
    val coordinate: Offset,
    val name: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Holds structured station objects instead of raw coordinates
            val metroStations = remember { mutableStateListOf<MetroStation>() }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF111111)) // Upgraded to a sleek dark mode theme
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val nextId = metroStations.size + 1
                            val newStation = MetroStation(
                                id = nextId,
                                coordinate = offset,
                                name = "Station $nextId"
                            )
                            metroStations.add(newStation)
                        }
                    }
            ) {
                // 1. Draw the Metro Lines connecting the stations sequentially
                if (metroStations.size > 1) {
                    for (i in 0 until metroStations.size - 1) {
                        drawLine(
                            color = Color(0xFF00BCD4), // Vibrant transit cyan line
                            start = metroStations[i].coordinate,
                            end = metroStations[i + 1].coordinate,
                            strokeWidth = 8f,
                            cap = StrokeCap.Round
                        )
                    }
                }

                // 2. Draw the physical Station Nodes on top of the lines
                metroStations.forEach { station ->
                    drawCircle(
                        color = Color.White,
                        radius = 14f,
                        center = station.coordinate
                    )
                    // Outer accent ring for visual polish
                    drawCircle(
                        color = Color(0xFF00BCD4),
                        radius = 20f,
                        center = station.coordinate,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
                    )
                }
            }
        }
    }
}
