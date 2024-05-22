package com.example.randomuserapp.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuserapp.core.ManageViewModels
import com.example.randomuserapp.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException("FragmentUserBinding == null")

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = requireActivity() as ManageViewModels
        viewModel = manageViewModels.viewModel(UserViewModel::class.java)

        binding.loadNextButton.setOnClickListener {
            manageViewModels.clear(UserViewModel::class.java)
            (requireActivity() as UserNavigation).navigateFromUserToLoad()
        }

        val userUiState: UserUiState = viewModel.init(savedInstanceState == null)
        with(binding) {
            userUiState.update(
                pictureImageView,
                firstNameTextView,
                lastNameTextView,
                genderTextView,
                phoneTextView,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface UserNavigation {
    fun navigateFromUserToLoad()
}