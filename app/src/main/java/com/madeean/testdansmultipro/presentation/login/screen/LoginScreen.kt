package com.madeean.testdansmultipro.presentation.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
  viewModel: LoginViewModel,
  navigateToHomeScreen: () -> Unit,
  onGoogleSignInClick: () -> Unit
) {
  Scaffold {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 32.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Blue
        ),
      ) {
        Text(text = "Continue with Facebook")
      }
      Spacer(modifier = Modifier.height(12.dp))
      Text(text = "OR")
      Spacer(modifier = Modifier.height(12.dp))
      Button(
        onClick = {onGoogleSignInClick()},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.White,
          contentColor = Color.DarkGray
        ),
        elevation = ButtonDefaults.buttonElevation(
          defaultElevation = 5.dp
        )
      ) {
        Text(text = "Sign in with Google")
      }
    }
  }
}