package com.madeean.testdansmultipro.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import com.madeean.testdansmultipro.domain.login.LoginDomainUseCase

class LoginViewModel(
  private val useCase: LoginDomainUseCase
): ViewModel() {
}