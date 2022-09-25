package com.soul.crm.presentation.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.data.remote.models.response.user.PeopleResult
import com.soul.crm.domain.student.use_case.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(private val studentUseCase: StudentUseCase) : ViewModel() {
    private val _student = MutableSharedFlow<BaseNetworkResult<PeoplePagination>>()
    val student = _student.asSharedFlow()

    fun getStudentList(page:Int) {
        viewModelScope.launch {
            studentUseCase.getStudent(page = page).onEach { result ->
                when (result) {
                    is BaseNetworkResult.Error -> {
                        _student.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _student.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _student.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}