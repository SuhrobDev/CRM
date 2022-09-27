package com.soul.crm.presentation.teachers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.teacher.use_case.TeacherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeachersViewModel @Inject constructor(private val teacherUseCase: TeacherUseCase): ViewModel() {
    private val _teacher = MutableSharedFlow<BaseNetworkResult<PeoplePagination>>()
    val teacher = _teacher.asSharedFlow()

    fun getTeacherList(page:Int) {
        viewModelScope.launch {
            teacherUseCase.getTeacher(page = page).onEach { result ->
                when (result) {
                    is BaseNetworkResult.Error -> {
                        _teacher.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _teacher.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _teacher.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}