package com.madeean.testdansmultipro.data.repository.datastore

import com.madeean.testdansmultipro.data.network.ApiService
import io.ktor.client.HttpClient

class JobDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {

}