package com.madeean.testdansmultipro.presentation.job.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import com.madeean.testdansmultipro.domain.job.JobDomainUseCase
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class JobViewModel(private val useCase: JobDomainUseCase) : ViewModel() {
  private var _listJob: MutableStateFlow<PagingData<JobDomainModel>> = MutableStateFlow(PagingData.empty())
  val listJob: StateFlow<PagingData<JobDomainModel>> = _listJob

  private var _detailJob: MutableStateFlow<RequestState<JobDomainModel>> = MutableStateFlow(RequestState.Idle)
  val detailJob: StateFlow<RequestState<JobDomainModel>> = _detailJob

  fun getListJob(description: String = "", location: String = "", full_time: Boolean = true) {
    viewModelScope.launch {
      useCase.getListJob(viewModelScope, description, location, full_time).collectLatest {
        _listJob.value = it
      }
    }
  }

  fun getDetailJob(idJob:String){
    viewModelScope.launch {
      useCase.getDetailJob(idJob).collectLatest {
        _detailJob.value = it
      }
    }
  }
}