package com.madeean.testdansmultipro.presentation.navigator.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madeean.testdansmultipro.presentation.login.screen.LoginScreen
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator

fun NavGraphBuilder.loginComposable(
  loginViewModel: LoginViewModel,
  navigateToHomeScreen: (String) -> Unit,
  onGoogleSignInClick: () -> Unit,
  onFacebookSignInClick: () -> Unit
) {
  composable(
    route = ConstantNavigator.LOGIN_SCREEN,
  ) {
    LoginScreen(loginViewModel, navigateToHomeScreen, onGoogleSignInClick, onFacebookSignInClick)
  }
}