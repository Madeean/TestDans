package com.madeean.testdansmultipro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.madeean.testdansmultipro.domain.login.model.LoginDetailModelDomain
import com.madeean.testdansmultipro.domain.login.model.LoginModelDomain
import com.madeean.testdansmultipro.presentation.login.auth.FacebookAuthUiClient
import com.madeean.testdansmultipro.presentation.login.auth.GoogleAuthUiClient
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel
import com.madeean.testdansmultipro.presentation.navigator.SetupNavigation
import org.koin.compose.currentKoinScope


class MainActivity : ComponentActivity() {
  lateinit var navController: NavHostController
  private lateinit var googleAuthUiClient: GoogleAuthUiClient
  private lateinit var firebaseAuth: FirebaseAuth
  private lateinit var facebookAuthUiClient: FacebookAuthUiClient
  private lateinit var callbackManager: CallbackManager
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    googleAuthUiClient = GoogleAuthUiClient(this)
    firebaseAuth = FirebaseAuth.getInstance()

    val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
      googleAuthUiClient.handleSignInResult(result.data, { loginModel ->
//        navController.navigate("${ConstantNavigator.HOME_SCREEN}/${loginModel.toQueryString()}")
                                                         println("LOGIN SUCCESS")
      }, {
        // Handle failure
        println("LOGIN FAILED")

      })
    }

    setContent {

      val loginViewModel = koinViewModel<LoginViewModel>()

      navController = rememberNavController()
      SetupNavigation(
        navController = navController,
        loginViewModel = loginViewModel,
        onGoogleSignInClick = {
          val signInIntent = googleAuthUiClient.signInIntent()
          signInLauncher.launch(signInIntent)
        },
        isUserLoggedIn = firebaseAuth.currentUser != null,
        userData = firebaseAuth.currentUser?.let { user ->
          val userDetails = LoginDetailModelDomain(
            userId = user.uid,
            username = user.displayName ?: "",
            profilePictureUrl = user.photoUrl?.toString()
          )
          LoginModelDomain(data = userDetails, errorMessage = null)
        }
      )
    }
  }
}

@Composable
inline fun <reified T : androidx.lifecycle.ViewModel> koinViewModel(): T {
  val scope = currentKoinScope()
  return viewModel {
    scope.get<T>()
  }
}

