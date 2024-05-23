package com.example.randomuserapp.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuserapp.core.di.ManageViewModels
import com.example.randomuserapp.databinding.FragmentLoadBinding

class LoadFragment : Fragment() {

    private var _binding: FragmentLoadBinding? = null
    private val binding: FragmentLoadBinding
        get() = _binding ?: throw RuntimeException("FragmentLoadBinding == null")

    private lateinit var viewModel: LoadViewModel
    private lateinit var showUi: (LoadUiState) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = requireActivity() as ManageViewModels
        viewModel = manageViewModels.viewModel(LoadViewModel::class.java)

        showUi = { loadUiState ->
            loadUiState.update(
                binding.progressBar,
                binding.errorTextView,
                binding.retryButton,
            )
            loadUiState.navigate {
                manageViewModels.clear(LoadViewModel::class.java)
                (requireActivity() as LoadNavigation).navigateFromLoadToUser()
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdates(showUi)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface LoadNavigation {
    fun navigateFromLoadToUser()
}