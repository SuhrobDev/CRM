package com.soul.crm.presentation.students

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.soul.crm.R
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.databinding.FragmentStudentsBinding
import com.soul.crm.presentation.adapters.peoples.TableViewAdapter
import com.soul.crm.presentation.base.BaseFragment
import com.soul.crm.presentation.main_activity.DrawerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentsFragment : BaseFragment<FragmentStudentsBinding>(FragmentStudentsBinding::inflate) {
    private val viewModel: StudentsViewModel by viewModels()
    private val adapter by lazy {
        activity?.let { TableViewAdapter(it) }
    }

    override fun onViewCreate() {
        setUp()
        observe()
        send()
    }

    private fun setUp(){
        binding.list.adapter = adapter
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.student.collect {
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
    private fun send(){
        viewModel.getStudentList(page = 1)
    }
}