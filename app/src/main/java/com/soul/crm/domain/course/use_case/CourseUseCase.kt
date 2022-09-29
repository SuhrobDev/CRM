package com.soul.crm.domain.course.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.course.CoursePagination
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.course.repo.CourseRepo
import com.soul.crm.domain.payment.repo.PaymentRepo
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseUseCase @Inject constructor(private val courseRepo: CourseRepo) {
    suspend fun getCourse(
        page: Int,
    ): Flow<BaseNetworkResult<CoursePagination>> {
        return courseRepo.getCourse(page = page)
    }
}