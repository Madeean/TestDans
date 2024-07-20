package com.madeean.testdansmultipro

import android.content.Intent
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
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.auth.FirebaseAuth
import com.madeean.testdansmultipro.domain.login.model.LoginDetailModelDomain
import com.madeean.testdansmultipro.domain.login.model.LoginModelDomain
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.login.auth.FacebookAuthUiClient
import com.madeean.testdansmultipro.presentation.login.auth.GoogleAuthUiClient
import com.madeean.testdansmultipro.presentation.login.auth.toQueryString
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel
import com.madeean.testdansmultipro.presentation.navigator.SetupNavigation
import com.madeean.testdansmultipro.presentation.util.ConstantNavigator
import org.koin.compose.currentKoinScope
import kotlin.math.log


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

    FacebookSdk.setClientToken(getString(R.string.facebook_client_token))
    FacebookSdk.sdkInitialize(applicationContext)
    AppEventsLogger.activateApp(application)
    callbackManager = CallbackManager.Factory.create()

    val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
      googleAuthUiClient.handleSignInResult(result.data, { loginModel ->
        navController.navigate("Home/${loginModel.toQueryString()}")
       println("LOGIN SUCCESS")
      }, {
        println("LOGIN FAILED")

      })
    }

    setContent {

      val loginViewModel = koinViewModel<LoginViewModel>()
      val jobViewModel = koinViewModel<JobViewModel>()

      navController = rememberNavController()
      facebookAuthUiClient = FacebookAuthUiClient(this, callbackManager, navController)

      SetupNavigation(
        navController = navController,
        loginViewModel = loginViewModel,
        jobViewModel = jobViewModel,
        onGoogleSignInClick = {
          val signInIntent = googleAuthUiClient.signInIntent()
          signInLauncher.launch(signInIntent)
        },
        isUserLoggedIn = firebaseAuth.currentUser != null,
        userData = firebaseAuth.currentUser?.let { user ->
          val userDetails = LoginDetailModelDomain(
            userId = user.uid,
            username = user.displayName ?: "",
            profilePictureUrl = user.photoUrl?.toString(),
            method = "unknown"
          )
          LoginModelDomain(data = userDetails, errorMessage = null)
        },
        onFacebookSignInClick = {
          facebookAuthUiClient.login()
        },
        logout = { logout() }
      )
    }
  }

  fun logout() {
    googleAuthUiClient.signOut()
    facebookAuthUiClient.signOut()
    navController.navigate("Login")
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    callbackManager.onActivityResult(requestCode, resultCode, data)
  }
}

@Composable
inline fun <reified T : androidx.lifecycle.ViewModel> koinViewModel(): T {
  val scope = currentKoinScope()
  return viewModel {
    scope.get<T>()
  }
}

