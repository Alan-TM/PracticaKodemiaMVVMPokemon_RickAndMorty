package mx.alan.practicakodemiamvvmpokemon_rickandmorty.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.core.ApiPokemon
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.core.RetrofitInstance
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.PokemonResponse
import retrofit2.Response

class RepositorioPokemonNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiPokemon::class.java)

    suspend fun obtenerPokemones(limit: Int): Response<PokemonResponse> = withContext(Dispatchers.IO){
        retrofit.obtenerPokemones(limit)
    }
}