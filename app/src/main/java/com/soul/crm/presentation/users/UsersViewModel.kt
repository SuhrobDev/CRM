package com.soul.crm.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.user.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val _user = MutableSharedFlow<BaseNetworkResult<PeoplePagination>>()
    val user = _user.asSharedFlow()

    fun getUserList(page:Int) {
        viewModelScope.launch {
            userUseCase.getUser(page = page).onEach { result ->
                when (result) {
                    is BaseNetworkResult.Error -> {
                        _user.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _user.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _user.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}