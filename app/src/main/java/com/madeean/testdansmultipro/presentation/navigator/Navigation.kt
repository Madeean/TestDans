package com.madeean.testdansmultipro.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.madeean.testdansmultipro.presentation.navigator.directions.homeComposable
import com.madeean.testdansmultipro.presentation.navigator.directions.loginComposable
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator

@Composable
fun SetupNavigation(
  navController: NavHostController,
//  viewModel: ViewModel
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  NavHost(
    navController = navController,
    startDestination = ConstantNavigator.LOGIN_SCREEN,
  ) {
    homeComposable()
    loginComposable(navigateToHomeScreen = screen.home)
//    detailComposable(viewModel, navController)
  }
}