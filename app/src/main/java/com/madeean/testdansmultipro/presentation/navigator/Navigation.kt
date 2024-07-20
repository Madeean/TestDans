package com.madeean.testdansmultipro.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.madeean.testdansmultipro.domain.login.model.LoginModelDomain
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel
import com.madeean.testdansmultipro.presentation.navigator.directions.homeComposable
import com.madeean.testdansmultipro.presentation.navigator.directions.loginComposable
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator

@Composable
fun SetupNavigation(
  navController: NavHostController,
  loginViewModel: LoginViewModel,
  onGoogleSignInClick: () -> Unit,
  isUserLoggedIn: Boolean,
  userData: LoginModelDomain?
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  LaunchedEffect(isUserLoggedIn) {
    if (isUserLoggedIn && userData != null) {
      navController.navigate(ConstantNavigator.HOME_SCREEN)
    }
  }

  NavHost(
    navController = navController,
    startDestination = if (isUserLoggedIn && userData != null) {
      ConstantNavigator.HOME_SCREEN
    } else {
      ConstantNavigator.LOGIN_SCREEN
    },
  ) {
    homeComposable()
    loginComposable(loginViewModel = loginViewModel, navigateToHomeScreen = screen.home, onGoogleSignInClick = onGoogleSignInClick)
  }
}