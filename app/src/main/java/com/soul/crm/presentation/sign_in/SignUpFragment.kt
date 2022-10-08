package com.soul.crm.presentation.sign_in

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentSignUpBinding
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate){
    private val viewModel : SignUpViewModel by viewModels()

    override fun onViewCreate() {
        send()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.signUp.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            Toast.makeText(requireContext(), "${it.data?.user?.first_name}", Toast.LENGTH_SHORT).show()
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.password.collect {
                    if (it.check) {
                        binding.passwordInput.isHelperTextEnabled = false
                    } else {
                        binding.passwordInput.isHelperTextEnabled = true
                        binding.passwordInput.helperText = it.error
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.name.collect {
                    if (it.check) {
                        binding.nameInput.isHelperTextEnabled = false
                    } else {
                        binding.nameInput.helperText = it.error
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.lastname.collect {
                    if (it.check) {
                        binding.lastnameInput.isHelperTextEnabled = false
                    } else {
                        binding.lastnameInput.helperText = it.error
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.phoneNumber.collect {
                    if (it.check) {
                        binding.phoneInput.isHelperTextEnabled = false
                    } else {
                        binding.phoneInput.isHelperTextEnabled = true
                        binding.phoneInput.helperText = it.error
                    }
                }
            }
        }
    }

    private fun send() {
        binding.apply {
            passwordInput.isHelperTextEnabled = false
            password.addTextChangedListener {
                viewModel.validatePassword(it.toString())
            }

            phoneInput.isHelperTextEnabled = false
            phone.addTextChangedListener {
                viewModel.validatePhoneNumber(it.toString())
            }

            lastnameInput.isHelperTextEnabled = false
            lastname.addTextChangedListener {
                viewModel.validateLastname(it.toString())
            }

            nameInput.isHelperTextEnabled = false
            name.addTextChangedListener {
                viewModel.validateName(it.toString())
            }

            signUp.setOnClickListener {
//                Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
                viewModel.signUp(name = name.text.toString().trim(), lastname = lastname.text.toString().trim(), password = password.text.toString().trim(), phoneNumber = phone.text.toString().trim(), image = null, gender = "male")
            }
        }
    }
}