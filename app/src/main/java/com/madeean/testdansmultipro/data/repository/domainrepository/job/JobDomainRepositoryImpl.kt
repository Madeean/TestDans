package com.madeean.testdansmultipro.data.repository.domainrepository.job

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.data.network.httpClient
import com.madeean.testdansmultipro.data.repository.datastore.job.JobDataStore
import com.madeean.testdansmultipro.data.repository.datastore.job.JobPagingDataSource
import com.madeean.testdansmultipro.domain.job.JobDomainRepository
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.util.RequestState
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JobDomainRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) : JobDomainRepository {
  private val dataStore = JobDataStore(httpClient, apiService)
  override fun getListJob(
    scope: CoroutineScope,
    description: String,
    location: String,
    full_time: Boolean
  ): Flow<PagingData<JobDomainModel>> = Pager(
  config = PagingConfig(pageSize = 18, initialLoadSize = 18, enablePlaceholders = false),
  pagingSourceFactory = {
    JobPagingDataSource(httpClient, apiService, description, location, full_time)
  }
  ).flow.cachedIn(scope)

  override fun getDetailJob(idJob: String): Flow<RequestState<JobDomainModel>> {
    return flow{
     emit(RequestState.Loading)
      val data = dataStore.getDetailJob(idJob)
      emit(data)
    }
  }
}