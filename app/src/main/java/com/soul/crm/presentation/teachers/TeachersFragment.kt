package com.soul.crm.presentation.teachers

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentTeachersBinding
import com.soul.crm.presentation.adapters.peoples.TableViewAdapter
import com.soul.crm.presentation.base.BaseFragment
import com.soul.crm.presentation.main_activity.DrawerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeachersFragment : BaseFragment<FragmentTeachersBinding>(FragmentTeachersBinding::inflate) {
    private val viewModel: TeachersViewModel by viewModels()
    private val adapter by lazy {
        activity?.let { TableViewAdapter(it) }
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
                viewModel.teacher.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapter?.setList(detail.results)
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
        viewModel.getTeacherList(page = 1)
    }
}