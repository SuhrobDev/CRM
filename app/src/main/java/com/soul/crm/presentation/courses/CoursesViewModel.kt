package com.soul.crm.presentation.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.course.CoursePagination
import com.soul.crm.domain.course.use_case.CourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(private val courseUseCase: CourseUseCase) : ViewModel() {
    private val _course = MutableSharedFlow<BaseNetworkResult<CoursePagination>>()
    val course = _course.asSharedFlow()

    fun getCourseList(page: Int) {
        viewModelScope.launch {
            courseUseCase.getCourse(page = page).onEach { result ->
                when (result) {
                    is BaseNetworkResult.Error -> {
                        _course.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _course.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _course.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}