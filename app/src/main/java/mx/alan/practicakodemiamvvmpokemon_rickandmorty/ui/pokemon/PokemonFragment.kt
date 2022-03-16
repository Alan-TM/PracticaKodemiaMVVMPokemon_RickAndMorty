package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.pokemon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.Pokemon
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel.fetchPokemons()

        initializeObservers()
    }

    private fun initializeObservers() {
        pokemonViewModel.pokemones.observe(viewLifecycleOwner, ::mostrarPokemones)

        //pokemonViewModel.errorResponse.observe(viewLifecycleOwner, ::showError)

        pokemonViewModel.isLoading.observe(viewLifecycleOwner, ::loading)
    }

    private fun loading(b: Boolean?) {
        //TODO
    }

    private fun showError(s: String?) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarPokemones(pokemones: ArrayList<Pokemon>) {
        for (pokemon in pokemones) {
            pokemon.name?.let { Log.d("POKEMON", it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}