package mx.alan.practicakodemiamvvmpokemon_rickandmorty.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.adapters.RickAndMortyAdapter
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: GalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllCharacters()

        viewModel.rickAndMortyResponse.observe(viewLifecycleOwner){chars ->
            with(binding.rvCharacters){
                adapter = RickAndMortyAdapter(chars)
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.page.observe(viewLifecycleOwner){
            viewModel.fetchAllCharacters()

            binding.buttonBack.isVisible = it != 1
        }

        initializeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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