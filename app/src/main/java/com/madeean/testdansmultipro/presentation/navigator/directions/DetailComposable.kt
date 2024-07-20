package com.madeean.testdansmultipro.presentation.navigator.directions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.madeean.testdansmultipro.presentation.home.screen.HomeScreen
import com.madeean.testdansmultipro.presentation.job.screen.detail.DetailJobScreen
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator
import com.madeean.testdansmultipro.presentation.util.parseQueryString

fun NavGraphBuilder.detailComposable(
  jobViewModel: JobViewModel,
  navController: NavController
) {
  composable(
    route = "Detail/{detailJob}",
    arguments = listOf(navArgument(ConstantNavigator.DETAIL_SCREEN_ARGUMENT_KEY) {
      type = NavType.StringType
    })
  ) {
    val idJob= it.arguments?.getString(ConstantNavigator.DETAIL_SCREEN_ARGUMENT_KEY) ?: ""
    DetailJobScreen(
      idJob = idJob,
      viewModel = jobViewModel,
      navController
    )
  }
}