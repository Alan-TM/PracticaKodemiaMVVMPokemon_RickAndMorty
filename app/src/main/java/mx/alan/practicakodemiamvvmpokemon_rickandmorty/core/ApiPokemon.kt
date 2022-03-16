package mx.alan.practicakodemiamvvmpokemon_rickandmorty.core

import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPokemon {
    @GET("pokemon?offset=200")
    suspend fun obtenerPokemones(@Query("limit") limit: Int): Response<PokemonResponse>
}