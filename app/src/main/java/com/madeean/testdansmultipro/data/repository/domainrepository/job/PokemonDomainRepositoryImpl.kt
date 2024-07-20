package com.madeean.testdansmultipro.data.repository.domainrepository.job
//
//import com.madeean.testdansmultipro.data.network.ApiService
//import com.madeean.testemos.data.repository.datastore.PokemonDataStore
//import com.madeean.testdansmultipro.domain.job.JobDomainRepository
//import com.madeean.testemos.domain.model.DetailPokemonModelDomain
//import com.madeean.testemos.domain.model.PokemonModelPresentation
//import com.madeean.testdansmultipro.presentation.util.RequestState
//import io.ktor.client.HttpClient
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//class PokemonDomainRepositoryImpl(
//  httpClient: HttpClient,
//  apiService: ApiService
//) : JobDomainRepository {
//  private val dataStore = PokemonDataStore(httpClient, apiService)
//
//  override fun getDetailPokemon(name: String): Flow<RequestState<DetailPokemonModelDomain>> {
//    return flow {
//      emit(RequestState.Loading)
//      val data = dataStore.getDetailPokemonByName(name)
//      emit(data)
//    }
//  }
//
//  override fun getListPokemon(): Flow<RequestState<List<PokemonModelPresentation>>> {
//    return flow {
//      emit(RequestState.Loading)
//      val data = dataStore.getListPokemonWithImage()
//      emit(data)
//    }
//  }
//
//}