package com.soul.crm.presentation.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.domain.payment.use_case.PaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentUseCase: PaymentUseCase) : ViewModel() {
    private val _payment = MutableSharedFlow<BaseNetworkResult<PaymentPagination>>()
    val payment = _payment.asSharedFlow()

    fun getPaymentList(page: Int) {
        viewModelScope.launch {
            paymentUseCase.getPayment(page = page).onEach { result ->
                when (result) {
                    is BaseNetworkResult.Error -> {
                        _payment.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _payment.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _payment.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}