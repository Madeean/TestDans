package com.madeean.testdansmultipro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madeean.testdansmultipro.presentation.navigator.SetupNavigation
import com.madeean.testdansmultipro.presentation.ui.theme.TestDansMultiProTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
  lateinit var navController: NavHostController
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
//      val viewModel = koinViewModel<ViewModel>()
      navController = rememberNavController()
      SetupNavigation(
        navController = navController,
      )
    }
  }
}

