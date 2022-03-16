package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.adapters.RickAndMortyAdapter
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllCharacters()

        initializeObservers()
        initializeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeObservers(){
        viewModel.rickAndMortyResponse.observe(viewLifecycleOwner){chars ->
            with(binding.rvCharacters){
                adapter = RickAndMortyAdapter(chars)
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.page.observe(viewLifecycleOwner){
            viewModel.fetchAllCharacters()

            binding.buttonBack.isEnabled = it != 1
        }
    }

    private fun initializeUI(){
        binding.buttonNext.setOnClickListener {
            viewModel.sumCounterAndFetchData()
        }

        binding.buttonBack.setOnClickListener {
            viewModel.subtractCounterAndFetchData()
        }
    }
}