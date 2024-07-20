package com.madeean.testdansmultipro.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
  innerPaddingValues: PaddingValues,
  userId: String,
  username: String,
  profilePicture: String,
  logout: ()-> Unit,
  method: String
) {
  var photoProfile = ""

  if (method == "google"){
    photoProfile = "https://lh3.googleusercontent.com/a/$profilePicture"
  }else if (method == "facebook"){
    photoProfile = "https://graph.facebook.com/${profilePicture}/picture"
  }else if(method == "unknown"){
    println("METHOD UNKNOWN")
  }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)
        .padding(top = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ){
      AsyncImage(model = photoProfile, contentDescription = null, modifier = Modifier.size(120.dp))
      Spacer(modifier = Modifier.height(10.dp))
      Text(username)
      Spacer(modifier = Modifier.height(10.dp))
      Button(onClick = {
        logout()
      }) {
        Text("Logout")
      }
    }
}