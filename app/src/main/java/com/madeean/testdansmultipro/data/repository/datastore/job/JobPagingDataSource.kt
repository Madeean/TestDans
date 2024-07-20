package com.madeean.testdansmultipro.data.repository.datastore.job

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.data.repository.network.job.model.JobDataModel
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Job

class JobPagingDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService,
  private val description: String,
  private val location: String,
  private val full_time: Boolean
) : PagingSource<Int, JobDomainModel>() {
  override fun getRefreshKey(state: PagingState<Int, JobDomainModel>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobDomainModel> {
    val position = params.key ?: 1
    return try {
      val response =
        httpClient.get("${apiService.BASE_URL_LIST_JOB}?page=$position&description=$description&location=$location&full_time=$full_time")

      if (response.status.value == 200) {
        val apiResponse = response.body<List<JobDataModel?>?>()
        val data = JobDataModel.transforms(apiResponse)

        toLoadResult(data = data, nextKey = if (data.isEmpty()) null else position + 1)
      } else {
        val data = listOf<JobDomainModel>()

        toLoadResult(data = data, nextKey = if (data.isEmpty()) null else position + 1)
      }
    }catch (e:Exception){
      LoadResult.Error(e)
    }
  }

  private fun toLoadResult(
    data: List<JobDomainModel>, prevKey: Int? = null, nextKey: Int? = null
  ): LoadResult<Int, JobDomainModel> {
    return LoadResult.Page(
      data = data, prevKey = prevKey, nextKey = nextKey
    )
  }
}