package com.madeean.testdansmultipro.data.repository.domainrepository

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.domain.JobDomainRepository
import io.ktor.client.HttpClient

class JobDomainRepositoryImpl(
  httpClient: HttpClient,
  apiService: ApiService
) : JobDomainRepository {}