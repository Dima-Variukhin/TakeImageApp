package com.example.takeimageapp.images.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.takeimageapp.databinding.FragmentImagesBinding
import com.example.takeimageapp.main.sl.ProvideViewModel

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ImagesViewModel.Base
    private val viewModelClass = ImagesViewModel.Base::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(viewModelClass, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        val adapter = ImagesAdapter(ImageLoader.Base(requireContext()))
        recyclerView.adapter = adapter

        viewModel.fetchImages("sci-fi", 1)

        viewModel.observeList(this) {
            adapter.map(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}