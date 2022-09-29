package com.soul.crm.presentation.courses

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentCoursesBinding
import com.soul.crm.presentation.adapters.course.TableCourseAdapter
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoursesFragment : BaseFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate) {
    private val viewModel: CoursesViewModel by viewModels()
    private val adapter by lazy {
        TableCourseAdapter()
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
                viewModel.course.collect {
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
        viewModel.getCourseList(page = 1)
    }

}