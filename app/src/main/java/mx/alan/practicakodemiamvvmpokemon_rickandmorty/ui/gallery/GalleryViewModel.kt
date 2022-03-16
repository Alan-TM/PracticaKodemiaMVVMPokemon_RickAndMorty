package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.rick_and_morty.Character
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.domain.GetRickAndMortyCharactersUseCase
import java.io.IOException

class GalleryViewModel : ViewModel() {
    private val _rickAndMortyResponse = MutableLiveData<ArrayList<Character>>()
    val rickAndMortyResponse: LiveData<ArrayList<Character>> get() = _rickAndMortyResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> get() = _page

    fun fetchAllCharacters(){
        _isLoading.value = true
        viewModelScope.launch {
            val response = GetRickAndMortyCharactersUseCase().invoke(_page.value!!)

            try {
                if (response.isSuccessful) {
                    _rickAndMortyResponse.value = response.body()
                } else{
                    _errorResponse.value = response.errorBody().toString()
                }

                _isLoading.value = false
            } catch(e: IOException){
                _errorResponse.value = e.localizedMessage
                _isLoading.value = false
            }
        }
    }

    fun sumCounterAndFetchData(){
        _page.value = _page.value?.plus(1)
    }

    fun subtractCounterAndFetchData(){
        _page.value = _page.value?.minus(1)
    }
}