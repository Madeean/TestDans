package com.madeean.testdansmultipro.data.repository.datastore.job

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.data.repository.network.job.model.JobDataModel
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.util.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class JobDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {
    suspend fun getDetailJob(idJob: String): RequestState<JobDomainModel> {
      return try{
        val response = httpClient.get("${apiService.BASE_URL_DETAIL_JOB}$idJob")
        if (response.status.value == 200) {
          val apiResponse = response.body<JobDataModel>()
          val data = JobDataModel.transform(apiResponse)
          RequestState.Success(data)
        } else {
          val error =
            Throwable(message = "HTTP ERROR ${response.status.value}, silahkan cek koneksi internet anda")
          RequestState.Error(error)
        }
      } catch (e: Exception){
        val error = Throwable(message = "${e.message}, silahkan cek koneksi anda")
        RequestState.Error(error)
      }
    }
}