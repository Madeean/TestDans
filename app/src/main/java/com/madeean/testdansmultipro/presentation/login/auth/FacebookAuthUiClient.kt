package com.madeean.testdansmultipro.presentation.login.auth

import android.app.Activity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.madeean.testdansmultipro.domain.login.model.LoginDetailModelDomain
import com.madeean.testdansmultipro.domain.login.model.LoginModelDomain

class FacebookAuthUiClient(
  private val activity: Activity,
  private val callbackManager: CallbackManager
) {
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

  fun login() {
    LoginManager.getInstance().logInWithReadPermissions(activity, listOf("email", "public_profile"))
    LoginManager.getInstance().registerCallback(callbackManager, object :
      FacebookCallback<LoginResult> {
      override fun onSuccess(loginResult: LoginResult) {
        handleFacebookAccessToken(loginResult.accessToken)
      }

      override fun onCancel() {
        println("Facebook login cancelled")
      }

      override fun onError(error: FacebookException) {
        println("Facebook login error: ${error.message}")
      }
    })
  }

  private fun handleFacebookAccessToken(token: AccessToken) {
    val credential = FacebookAuthProvider.getCredential(token.token)
    firebaseAuth.signInWithCredential(credential)
      .addOnCompleteListener(activity) { task ->
        if (task.isSuccessful) {
          val user = firebaseAuth.currentUser
          if (user != null) {
            val userDetails = LoginDetailModelDomain(
              userId = user.uid,
              username = user.displayName ?: "",
              profilePictureUrl = user.photoUrl?.toString()
            )
            val loginModel = LoginModelDomain(
              data = userDetails,
              errorMessage = null
            )
            println("Facebook login success")
          } else {
            println("Facebook login failed: user is null")
          }
        } else {
          println("Facebook login failed: ${task.exception?.message}")
        }
      }
  }
}
