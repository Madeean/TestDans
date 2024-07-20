package com.madeean.testdansmultipro.di

import com.madeean.testdansmultipro.data.network.ApiService
import com.madeean.testdansmultipro.data.network.httpClient
import com.madeean.testdansmultipro.data.repository.domainrepository.job.JobDomainRepositoryImpl
import com.madeean.testdansmultipro.data.repository.domainrepository.login.LoginDomainRepositoryImpl
import com.madeean.testdansmultipro.domain.job.JobDomainRepository
import com.madeean.testdansmultipro.domain.job.JobDomainUseCase
import com.madeean.testdansmultipro.domain.job.JobDomainUseCaseImpl
import com.madeean.testdansmultipro.domain.login.LoginDomainRepository
import com.madeean.testdansmultipro.domain.login.LoginDomainUseCase
import com.madeean.testdansmultipro.domain.login.LoginDomainUseCaseImpl
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.login.viewmodel.LoginViewModel
import org.koin.dsl.module

val appModule = module {

  single {  }

  single { httpClient }
  single { ApiService() }

  single { LoginDomainRepositoryImpl(get(), get())}
  single { LoginDomainUseCaseImpl(get()) }
  single<LoginDomainUseCase> { LoginDomainUseCaseImpl(get()) }
  single<LoginDomainRepository> { LoginDomainRepositoryImpl(get(), get()) }

  factory { LoginViewModel(get()) }


  single { JobDomainRepositoryImpl(get(), get()) }
  single { JobDomainUseCaseImpl(get()) }
  single<JobDomainUseCase> { JobDomainUseCaseImpl(get()) }
  single<JobDomainRepository> { JobDomainRepositoryImpl(get(), get()) }

  factory { JobViewModel(get()) }
}