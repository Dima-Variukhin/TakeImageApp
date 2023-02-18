package com.example.takeimageapp.images.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.takeimageapp.databinding.FragmentImagesBinding
import com.example.takeimageapp.main.sl.ProvideViewModel
import com.google.android.material.textfield.TextInputEditText

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var textInputEditText: TextInputEditText

    private val watcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(text: Editable?) = viewModel.clearError()
    }

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
        val inputLayout = binding.textInputLayout
        val progressBar = binding.progressBar
        val getImageBtn = binding.getImageBtn
        val getRandomImageBtn = binding.randomImageBtn
        textInputEditText = binding.textInputEdittext
        val adapter = ImagesAdapter(ImageLoader.Base(requireContext()))
        recyclerView.adapter = adapter

        getImageBtn.setOnClickListener {
            viewModel.fetchImages(textInputEditText.text.toString(), 1)//TODO add page
        }

        viewModel.observeList(this) {
            adapter.map(it)
        }

        viewModel.observeProgress(this) {
            progressBar.visibility = it
        }

        viewModel.observeState(this) {
            it.apply(inputLayout)
        }
    }

    override fun onResume() {
        textInputEditText.addTextChangedListener(watcher)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        textInputEditText.removeTextChangedListener(watcher)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    override fun afterTextChanged(text: Editable?) = Unit
}