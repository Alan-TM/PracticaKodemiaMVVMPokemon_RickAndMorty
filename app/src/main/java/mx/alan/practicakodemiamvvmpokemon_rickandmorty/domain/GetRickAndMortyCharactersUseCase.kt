package mx.alan.practicakodemiamvvmpokemon_rickandmorty.domain

import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.rick_and_morty.Character
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.repository.RepositorioRickAndMortyNetwork
import retrofit2.Response

class GetRickAndMortyCharactersUseCase {
    private val service = RepositorioRickAndMortyNetwork()

    suspend operator fun invoke(page: Int): Response<ArrayList<Character>> {
        val response = service.getAllCharacters(page).body()?.results
        return Response.success(response)
    }
}