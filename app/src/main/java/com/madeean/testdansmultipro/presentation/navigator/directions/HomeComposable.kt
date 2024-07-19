package com.madeean.testdansmultipro.presentation.navigator.directions

import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madeean.testdansmultipro.presentation.home.screen.HomeScreen
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator

fun NavGraphBuilder.homeComposable() {
  composable(
    route = ConstantNavigator.HOME_SCREEN,
  ) {
    HomeScreen()
  }
}