package com.madeean.testdansmultipro.data.repository.datastore.login

import com.madeean.testdansmultipro.data.network.ApiService
import io.ktor.client.HttpClient

class LoginDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {

}