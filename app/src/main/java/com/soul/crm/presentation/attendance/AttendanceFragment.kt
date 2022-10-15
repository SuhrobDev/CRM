package com.soul.crm.presentation.attendance

import android.widget.ArrayAdapter
import com.soul.crm.R
import com.soul.crm.databinding.FragmentAttendanceBinding
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendanceFragment :
    BaseFragment<FragmentAttendanceBinding>(FragmentAttendanceBinding::inflate) {
    override fun onViewCreate() {
        val mCourseList = arrayOf<String?>("Math", "English", "Programming")
        val mGroupList = arrayOf<String?>("A1", "B2", "A4","G30")
        val mArrayAdapterC = activity?.applicationContext?.let {
            ArrayAdapter<String?>(
                it,
                R.layout.spinner_list,
                mCourseList
            )
        }
        val mArrayAdapterG = activity?.applicationContext?.let {
            ArrayAdapter<String?>(
                it,
                R.layout.spinner_list,
                mGroupList
            )
        }
        mArrayAdapterC?.setDropDownViewResource(R.layout.spinner_list)
        mArrayAdapterG?.setDropDownViewResource(R.layout.spinner_list)

        binding.courseSpinner.adapter = mArrayAdapterC
        binding.groupSpinner.adapter = mArrayAdapterG
    }


}