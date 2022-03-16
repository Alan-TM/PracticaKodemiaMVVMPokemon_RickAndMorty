package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.pokemon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.ErrorResponse
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.Pokemon
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.domain.GetPokemonByNetworkUseCase
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class PokemonViewModelTest{

    private lateinit var viewModel: PokemonViewModel

    //crea la funcion a mockear
    @RelaxedMockK
    private lateinit var casoUso: GetPokemonByNetworkUseCase

    //creamos una regla
    @get:Rule
    var schedulers: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        //inicializar el mock
        MockKAnnotations.init(this)

        //init el view model
        viewModel = PokemonViewModel()

        //Configurando el dispatcher a un hilo distinto por defecto
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel.useCase = casoUso
    }

    @After
    fun onAfter(){
        //reset conf de dispatchers
        Dispatchers.resetMain()
    }

    @Test
    fun checarPokemonesUseCase() = runTest {
        val pokemones = ArrayList<Pokemon>()
        pokemones.add(Pokemon("Sandslash"))
        pokemones.add(Pokemon("Dragonair"))

        //configuracion
        coEvery { casoUso.invoke() } returns Response.success(pokemones)

        //cuando se va a ejecutar
        viewModel.fetchPokemons()

        //respuesta
        assert(viewModel.pokemones.value == pokemones)
    }

    @Test
    fun checarError() = runTest{
        val string ="{" +
                "protocol:\"h2\",\n" +
                " code:400,\n" +
                " message:\"error\", \n" +
                "url:\"https://rickandmortyapi.com/api/character\"\n" +
                "}"

        val response = ResponseBody.create(MediaType.parse("text/*"), string)
        coEvery {
            casoUso.invoke()
        } returns Response.error(400, response)

        viewModel.fetchPokemons()

        val errorViewModel = viewModel.errorResponse.value as ErrorResponse

        assert(errorViewModel.code == 400)
    }
}