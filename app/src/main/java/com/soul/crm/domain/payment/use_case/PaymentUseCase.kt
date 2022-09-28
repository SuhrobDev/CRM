package com.soul.crm.domain.payment.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.payment.repo.PaymentRepo
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentUseCase @Inject constructor(private val paymentRepo: PaymentRepo) {
    suspend fun getPayment(
        page: Int,
    ): Flow<BaseNetworkResult<PaymentPagination>> {
        return paymentRepo.getPayment(page = page)
    }
}