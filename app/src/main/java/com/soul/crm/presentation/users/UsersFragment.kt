package com.soul.crm.presentation.users

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentUsersBinding
import com.soul.crm.presentation.adapters.peoples.TableViewAdapter
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {
    private val viewModel: UsersViewModel by viewModels()
    private val adapter by lazy {
        TableViewAdapter()
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
                viewModel.user.collect {
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
        viewModel.getUserList(page = 1)
    }

}