package com.soul.crm.presentation.payment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentPaymentBinding
import com.soul.crm.presentation.adapters.payment.TablePaymentAdapter
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    private val viewModel: PaymentViewModel by viewModels()
    private val adapter by lazy {
        TablePaymentAdapter()
    }

    override fun onViewCreate() {
        setUp()
        observe()
        send()
    }

    private fun setUp() {
        binding.list.adapter = adapter
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.payment.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapter.setList(detail.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }
    }

    private fun send() {
        viewModel.getPaymentList(page = 1)
    }

}