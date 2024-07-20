package com.madeean.testdansmultipro.data.repository.datastore.job
//
//import com.madeean.testdansmultipro.data.network.ApiService
//import com.madeean.testemos.data.network.ApiService
//import com.madeean.testemos.data.repository.network.model.DetailPokemonModelResponse
//import com.madeean.testemos.data.repository.network.model.PokemonDetailModelResponse
//import com.madeean.testemos.data.repository.network.model.PokemonModelResponse
//import com.madeean.testemos.domain.model.DetailPokemonModelDomain
//import com.madeean.testemos.domain.model.PokemonDetailModelDomain
//import com.madeean.testemos.domain.model.PokemonModelPresentation
//import com.madeean.testdansmultipro.presentation.util.RequestState
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.request.get
//
//class PokemonDataStore(
//  private val httpClient: HttpClient,
//  private val apiService: ApiService
//) {
//  private suspend fun getListPokemon(): RequestState<List<PokemonDetailModelDomain>> {
//    return try {
//      val response = httpClient.get(apiService.BASE_URL_POKEMON)
//
//      if (response.status.value == 200) {
//        val apiResponse = response.body<PokemonModelResponse>()
//        val data = PokemonDetailModelResponse.transforms(apiResponse.results)
//        RequestState.Success(data)
//      } else {
//        val error =
//          Throwable(message = "HTTP ERROR ${response.status.value}, silahkan cek koneksi internet anda")
//        RequestState.Error(error)
//      }
//    } catch (e: Exception) {
//      val error = Throwable(message = "${e.message}, silahkan cek koneksi anda")
//      RequestState.Error(error)
//    }
//  }
//
//  private suspend fun getDetailPokemon(url: String): RequestState<DetailPokemonModelDomain> {
//    return try {
//      val response = httpClient.get(url)
//
//      if (response.status.value == 200) {
//        val apiResponse = response.body<DetailPokemonModelResponse>()
//        val data = DetailPokemonModelResponse.transform(apiResponse)
//        RequestState.Success(data)
//      } else {
//        val error =
//          Throwable(message = "HTTP ERROR ${response.status.value}, silahkan cek koneksi internet anda")
//        RequestState.Error(error)
//      }
//    } catch (e: Exception) {
//      val error = Throwable(message = "${e.message}, silahkan cek koneksi anda")
//      RequestState.Error(error)
//    }
//  }
//
//  suspend fun getListPokemonWithImage(): RequestState<List<PokemonModelPresentation>> {
//    return try {
//      val listPokemonState = getListPokemon()
//      if (listPokemonState is RequestState.Success) {
//        val detailedPokemon = listPokemonState.data.map { pokemon ->
//          when (val detailState = getDetailPokemon(pokemon.url)) {
//            is RequestState.Success -> {
//              PokemonModelPresentation(
//                name = pokemon.name,
//                url = pokemon.url,
//                sprites = detailState.data.sprites,
//                height = detailState.data.height,
//                weight = detailState.data.weight
//              )
//            }
//            else -> {
//              null
//            }
//          }
//        }.filterNotNull()
//        RequestState.Success(detailedPokemon)
//      } else {
//        val error =
//          Throwable(message = "HTTP error data tidak ada, silahkan cek koneksi internet anda")
//        RequestState.Error(error)
//      }
//    } catch (e: Exception) {
//      val error = Throwable(message = "${e.message}, silahkan cek koneksi anda")
//      RequestState.Error(error)
//    }
//  }
//
//  suspend fun getDetailPokemonByName(name: String): RequestState<DetailPokemonModelDomain> {
//    return try {
//      val response = httpClient.get("${apiService.BASE_URL_POKEMON}/$name")
//
//      if (response.status.value == 200) {
//        val apiResponse = response.body<DetailPokemonModelResponse>()
//        val data = DetailPokemonModelResponse.transform(apiResponse)
//        RequestState.Success(data)
//      } else {
//        val error =
//          Throwable(message = "HTTP ERROR ${response.status.value}, silahkan cek koneksi internet anda")
//        RequestState.Error(error)
//      }
//    } catch (e: Exception) {
//      val error = Throwable(message = "${e.message}, silahkan cek koneksi anda")
//      RequestState.Error(error)
//    }
//  }
//}