package com.soul.crm.domain.course.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.course.CoursePagination
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import kotlinx.coroutines.flow.Flow

interface CourseRepo {
    suspend fun getCourse(page: Int): Flow<BaseNetworkResult<CoursePagination>>
}