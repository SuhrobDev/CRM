package com.soul.crm.data.repo.course

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.remote.models.response.course.CoursePagination
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.course.repo.CourseRepo
import com.soul.crm.domain.payment.repo.PaymentRepo
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(private val service: ApiService) : CourseRepo {
    override suspend fun getCourse(page: Int): Flow<BaseNetworkResult<CoursePagination>> = flow {
        val response = service.getCourse(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }
}