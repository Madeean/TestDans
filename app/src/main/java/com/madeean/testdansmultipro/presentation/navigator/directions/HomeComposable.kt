package com.madeean.testdansmultipro.presentation.navigator.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.madeean.testdansmultipro.presentation.home.screen.HomeScreen
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator
import com.madeean.testdansmultipro.presentation.util.parseQueryString
import kotlin.math.log

fun NavGraphBuilder.homeComposable(
  jobViewModel: JobViewModel,
  logout: () -> Unit,
  navigateToDetailScreen: (String) -> Unit
) {
  composable(
    route = "Home/{detailProfile}",
    arguments = listOf(navArgument(ConstantNavigator.HOME_SCREEN_ARGUMENT_KEY) {
      type = NavType.StringType
    })
  ) {
    val detailProfile = it.arguments?.getString(ConstantNavigator.HOME_SCREEN_ARGUMENT_KEY) ?: ""
    val userData = parseQueryString(detailProfile)
    HomeScreen(
      jobViewModel,
      userId = userData["userId"].orEmpty(),
      username = userData["username"].orEmpty(),
      profilePicture = userData["profilePictureUrl"].orEmpty(),
      logout,
      navigateToDetailScreen = navigateToDetailScreen,
      method = userData["method"].orEmpty()
    )
  }
}