package com.madeean.testdansmultipro.presentation.navigator

import androidx.navigation.NavHostController

class Screens(navController: NavHostController) {

  val home: (String) -> Unit = {
    navController.navigate(route = "Home/${it}")
  }

  val detail: (String) -> Unit = {
    navController.navigate(route = "Detail/${it}")
  }
}