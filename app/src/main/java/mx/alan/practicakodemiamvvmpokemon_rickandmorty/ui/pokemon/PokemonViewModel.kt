package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.ErrorResponse
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.Pokemon
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.domain.GetPokemonByNetworkUseCase
import java.io.IOException

class PokemonViewModel : ViewModel() {

    private val _pokemones = MutableLiveData<ArrayList<Pokemon>>()
    val pokemones: LiveData<ArrayList<Pokemon>> get() = _pokemones

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> get() = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    var useCase = GetPokemonByNetworkUseCase()


    fun fetchPokemons(){
        _isLoading.value = true
        viewModelScope.launch {
            val response = useCase.invoke()
            try {
                if (response.isSuccessful && response.code() == 200) {
                    _pokemones.value = response.body()
                } else if(response.code() == 400){
                    val gson = Gson()
                    val errorResponse = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                    _errorResponse.value = errorResponse
                }
                _isLoading.value = false
            }catch(e: IOException){
                _isLoading.value = false
                //_errorResponse.value = e.localizedMessage
                _errorResponse.value = e.message?.let { ErrorResponse(null, 400) }

            }
        }
    }
}