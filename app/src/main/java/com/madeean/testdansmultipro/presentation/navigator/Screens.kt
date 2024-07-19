package com.madeean.testdansmultipro.presentation.navigator

import androidx.navigation.NavHostController

class Screens(navController: NavHostController) {

  val home: () -> Unit = {
    navController.navigate(route = "Home")
  }

//  val detail: (String) -> Unit = {
//    navController.navigate(route = "detail_screen/${it}")
//  }
}