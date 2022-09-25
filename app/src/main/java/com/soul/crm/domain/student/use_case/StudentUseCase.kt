package com.soul.crm.domain.student.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentUseCase @Inject constructor(private val studentRepo: StudentRepo) {
    suspend fun getStudent(
        page: Int,
    ): Flow<BaseNetworkResult<PeoplePagination>> {
        return studentRepo.getStudent(page = page)
    }
}