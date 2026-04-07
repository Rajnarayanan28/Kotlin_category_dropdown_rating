package com.example.ca1_cse225

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ca1_cse225.ui.theme.CA1_CSE225Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechnologyDashboard()
        }
    }
}

@Composable
fun TechnologyDashboard() {

    val categories = listOf("Categorie 1", "Catogory2", "Catogory3", "Catogory4")
    val technologies = listOf("java", "Android", "Ai", "kotlin", "Cybersecurity")

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(categories[0]) }

    val ratings = remember { mutableStateMapOf<String, Int>() }

    Column(modifier = Modifier.padding(30.dp)) {

        Box {
            Button(onClick = { expanded = true }) {
                Text(selected)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selected = item
                            expanded = false
                        }
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {

            items(technologies) { tech ->

                val rating = ratings[tech] ?: 0

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {

                    Text(text = tech)

                    Row {

                        for (i in 1..5) {

                            Icon(
                                imageVector =
                                    if (i <= rating)
                                        Icons.Default.Star
                                    else
                                        Icons.Outlined.Star,


                                contentDescription = null,

                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        ratings[tech] = i
                                    }
                            )
                        }
                    }

                    Text("Rating: ${ratings[tech] ?: 0}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CA1_CSE225Theme {
        TechnologyDashboard()

    }
}