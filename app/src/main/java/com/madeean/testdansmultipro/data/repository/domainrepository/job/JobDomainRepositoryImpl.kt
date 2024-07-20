package com.madeean.testdansmultipro.data.repository.domainrepository.job

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.domain.job.JobDomainRepository
import io.ktor.client.HttpClient

class JobDomainRepositoryImpl(
  httpClient: HttpClient,
  apiService: ApiService
) : JobDomainRepository {}