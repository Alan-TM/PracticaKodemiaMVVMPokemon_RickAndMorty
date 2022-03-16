package mx.alan.practicakodemiamvvmpokemon_rickandmorty.domain

import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.Pokemon
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.repository.RepositorioPokemonNetwork
import retrofit2.Response

class GetPokemonByNetworkUseCase {
    private val service = RepositorioPokemonNetwork()

    suspend operator fun invoke(): Response<ArrayList<Pokemon>>{
        val response = service.obtenerPokemones(100).body()?.results
        return Response.success(response)
    }
}