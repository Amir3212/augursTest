package com.inerviewapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.inerviewapp.viewModels.LoginEvent
import com.inerviewapp.viewModels.LoginViewModel
import com.inerviewapp.databinding.FragmentFirstBinding
import com.inerviewapp.navigation.navigateToHomeScreen
import com.inerviewapp.utils.extension.showToast
import com.inerviewapp.utils.flowCollector.collectFlow
import com.inerviewapp.utils.network.ApiEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        collectFlow(viewModel.loginState) {
            binding.loginState = it

            it.message?.let { msg ->
                showToast(msg)
            }


            if (it.isLoggedInSuccessfully) {
                navigateToHomeScreen()
            }
        }
        collectFlow(viewModel.loginChannel) {
            when (it) {
                is ApiEvents.Failure -> {
                    showToast(it.error as String)
                }

                is ApiEvents.Loading -> {

                }

                is ApiEvents.Success<*> -> {
                    showToast(it.data as String)
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            etEmail.addTextChangedListener {
                it?.let { text ->
                    val event = LoginEvent.EmailEvent(text.toString())
                    viewModel.onEvent(event)
                }
            }
            etPassword.addTextChangedListener {
                it?.let { text ->
                    val event = LoginEvent.PasswordEvent(text.toString())
                    viewModel.onEvent(event)
                }
            }

            textView2.setOnClickListener {
                val event = LoginEvent.Login
                viewModel.onEvent(event)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}