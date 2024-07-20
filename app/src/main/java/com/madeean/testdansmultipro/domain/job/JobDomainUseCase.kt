package com.madeean.testdansmultipro.domain.job

import app.cash.paging.PagingData
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.util.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface JobDomainUseCase {
  fun getListJob(scope: CoroutineScope,description: String, location: String, full_time: Boolean): Flow<PagingData<JobDomainModel>>

  fun getDetailJob(idJob: String): Flow<RequestState<JobDomainModel>>
}