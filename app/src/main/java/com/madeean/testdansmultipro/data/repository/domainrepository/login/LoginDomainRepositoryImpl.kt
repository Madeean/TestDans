package com.madeean.testdansmultipro.data.repository.domainrepository.login

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.domain.login.LoginDomainRepository
import io.ktor.client.HttpClient

class LoginDomainRepositoryImpl(
  httpClient: HttpClient,
  apiService: ApiService,
) : LoginDomainRepository {
}