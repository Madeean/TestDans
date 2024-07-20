package com.madeean.testdansmultipro.domain.login


class LoginDomainUseCaseImpl(
  private val repository: LoginDomainRepository
) : LoginDomainUseCase {
  //
//  override fun getDetailPokemon(name: String): Flow<RequestState<DetailPokemonModelDomain>> {
//    return repository.getDetailPokemon(name)
//  }
//
//  override fun getListPokemon(): Flow<RequestState<List<PokemonModelPresentation>>> {
//    return repository.getListPokemon()
//  }
//  override fun handleSignInResult(
//    data: Intent?,
//    onSuccess: (LoginModelDomain) -> Unit,
//    onFailure: () -> Unit
//  ) {
//    return repository.handleSignInResult(data,onSuccess,onFailure)
//  }
//
//  override fun signInIntent(): Intent {
//    return repository.signInIntent()
//  }
}