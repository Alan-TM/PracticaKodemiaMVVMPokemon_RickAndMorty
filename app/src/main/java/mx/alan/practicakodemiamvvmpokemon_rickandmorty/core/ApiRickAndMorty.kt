package mx.alan.practicakodemiamvvmpokemon_rickandmorty.core

import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.rick_and_morty.AllCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRickAndMorty {
    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<AllCharacters>
}