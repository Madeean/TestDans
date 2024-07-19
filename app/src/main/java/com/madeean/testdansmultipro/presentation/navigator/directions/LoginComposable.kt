package com.madeean.testdansmultipro.presentation.navigator.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madeean.testdansmultipro.presentation.login.screen.LoginScreen
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator

fun NavGraphBuilder.loginComposable(
  navigateToHomeScreen: () -> Unit
) {
  composable(
    route = ConstantNavigator.LOGIN_SCREEN,
  ) {
    LoginScreen()
  }
}