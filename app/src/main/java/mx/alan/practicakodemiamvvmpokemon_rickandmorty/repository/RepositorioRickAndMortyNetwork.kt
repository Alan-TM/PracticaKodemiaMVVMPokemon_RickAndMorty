package mx.alan.practicakodemiamvvmpokemon_rickandmorty.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.core.ApiRickAndMorty
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.core.RetrofitInstance
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.rick_and_morty.AllCharacters
import retrofit2.Response

class RepositorioRickAndMortyNetwork {
    private val retrofit = RetrofitInstance.getRetrofitRickMorty().create(ApiRickAndMorty::class.java)

    suspend fun getAllCharacters(page: Int): Response<AllCharacters> = withContext(Dispatchers.IO){
        retrofit.getCharacters(page)
    }
}