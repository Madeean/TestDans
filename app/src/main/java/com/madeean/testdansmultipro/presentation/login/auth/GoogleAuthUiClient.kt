package com.madeean.testdansmultipro.presentation.login.auth

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.madeean.testdansmultipro.R
import com.madeean.testdansmultipro.domain.login.model.LoginDetailModelDomain
import com.madeean.testdansmultipro.domain.login.model.LoginModelDomain

class GoogleAuthUiClient(
  private val activity: Activity
) {
  private val googleSignInClient: GoogleSignInClient
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

  init {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken(activity.getString(R.string.web_client_id))
      .requestEmail()
      .build()
    googleSignInClient = GoogleSignIn.getClient(activity, gso)
  }

  fun signInIntent(): Intent {
    return googleSignInClient.signInIntent
  }

  fun handleSignInResult(intent: Intent?, onSuccess: (LoginModelDomain) -> Unit, onFailure: () -> Unit) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
    try {
      val account = task.getResult(ApiException::class.java)
      if (account != null) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
          .addOnCompleteListener(activity) { authResult ->
            if (authResult.isSuccessful) {
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
                onSuccess(loginModel)
              } else {
                onFailure()
              }
            } else {
              onFailure()
            }
          }
      } else {
        onFailure()
      }
    } catch (e: ApiException) {
      onFailure()
    }
  }
}

fun LoginModelDomain.toQueryString(): String {
  return "userId=${data?.userId.orEmpty()}&username=${data?.username.orEmpty()}&profilePictureUrl=${data?.profilePictureUrl.orEmpty()}"
}