package com.example.todo.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todo.domain.model.NavItem

@Composable
fun MainScreen() {

    val navItemList = listOf(
        NavItem("Today", Icons.Default.Done),
        NavItem("Upcoming", Icons.Default.DateRange),
        NavItem("Search", Icons.Default.Search),
        NavItem("Review", Icons.Default.MailOutline),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index},
                        icon = {
                            Icon(item.icon, "menu")
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(selectedIndex, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ContentScreen(selectedIndex: Int, modifier: Modifier) {

        when (selectedIndex) {
            0 -> TodayScreen(modifier)
            1 -> UpcomingScreen()
            2 -> SearchScreen()
            3 -> ReviewScreen()
        }

}
