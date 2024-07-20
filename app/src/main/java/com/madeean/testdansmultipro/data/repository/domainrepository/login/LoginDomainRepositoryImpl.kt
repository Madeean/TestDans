package com.madeean.testdansmultipro.data.repository.domainrepository.login

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.domain.login.LoginDomainRepository
import io.ktor.client.HttpClient

class LoginDomainRepositoryImpl(
  httpClient: HttpClient,
  apiService: ApiService,
) : LoginDomainRepository {
//  private val googleSignInClient: GoogleSignInClient
//  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
//
//  init {
//    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//      .requestIdToken(application.getString(R.string.web_client_id))
//      .requestEmail()
//      .build()
//    googleSignInClient = GoogleSignIn.getClient(application, gso)
//  }
//
//  override fun handleSignInResult(
//    data: Intent?,
//    onSuccess: (LoginModelDomain) -> Unit,
//    onFailure: () -> Unit
//  ) {
//    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//    try {
//      val account = task.getResult(ApiException::class.java)
//      if (account != null) {
//        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//        firebaseAuth.signInWithCredential(credential)
//          .addOnCompleteListener { authResult ->
//            if (authResult.isSuccessful) {
//              val user = firebaseAuth.currentUser
//              if (user != null) {
//                val userDetails = LoginDetailModelDomain(
//                  userId = user.uid,
//                  username = user.displayName ?: "",
//                  profilePictureUrl = user.photoUrl?.toString()
//                )
//                val loginModel = LoginModelDomain(
//                  data = userDetails,
//                  errorMessage = null
//                )
//                onSuccess(loginModel)
//              } else {
//                onFailure()
//              }
//            } else {
//              onFailure()
//            }
//          }
//      } else {
//        onFailure()
//      }
//    } catch (e: ApiException) {
//      onFailure()
//    }
//  }
//
//  override fun signInIntent(): Intent {
//    return googleSignInClient.signInIntent
//  }
}