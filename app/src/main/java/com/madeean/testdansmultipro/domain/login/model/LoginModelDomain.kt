package com.madeean.testdansmultipro.domain.login.model

data class LoginModelDomain(
  val data: LoginDetailModelDomain?,
  val errorMessage: String?,
)

data class LoginDetailModelDomain(
  val userId: String,
  val username: String,
  val profilePictureUrl: String?
)
