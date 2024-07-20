package com.madeean.testdansmultipro.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.unit.dp
import com.madeean.testdansmultipro.presentation.job.screen.JobScreen
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  jobViewModel: JobViewModel,
  userId: String,
  username: String,
  profilePicture: String,
  logout: () -> Unit,
  navigateToDetailScreen: (String) -> Unit,
  method: String
) {
  var selectedItem by remember { mutableStateOf(0) }
  val items = listOf("Job", "Profile")

  Scaffold(
    topBar = {
      Surface(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
      ) {
        CenterAlignedTopAppBar(
          colors = TopAppBarColors(
            containerColor = Color.LightGray,
            scrolledContainerColor = Color.LightGray,
            navigationIconContentColor = Color.DarkGray,
            titleContentColor = Color.DarkGray,
            actionIconContentColor = Color.DarkGray
          ),
          title = {
            Text(
              when (selectedItem) {
                0 -> "Job"
                1 -> "Profile"
                else -> ""
              }
            )
          },
        )
      }
    },
    bottomBar = {
      Surface(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.surface,
      ) {
        NavigationBar(
          containerColor = Color.LightGray
        ) {
          items.forEachIndexed { index, item ->
            NavigationBarItem(
              alwaysShowLabel = selectedItem == index,
              icon = {
                Icon(
                  imageVector = when (index) {
                    0 -> Icons.Default.Home
                    1 -> Icons.Filled.Person
                    else -> Icons.Filled.Person
                  },
                  contentDescription = item
                )
              },
              label = { Text(item) },
              selected = selectedItem == index,
              onClick = { selectedItem = index },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.DarkGray,
                unselectedTextColor = Color.Black,
                indicatorColor = Color.Transparent
              )
            )
          }
        }
      }
    }
  ) {
    when (selectedItem) {
      0 -> JobScreen(
        innerPaddingValues = it,
        jobViewModel,
        navigateToDetailScreen
      )
      1 -> ProfileScreen(innerPaddingValues = it,userId = userId, username = username, profilePicture = profilePicture, logout, method)
    }
  }
}