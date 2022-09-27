package com.soul.crm.domain.teacher.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.teacher.repo.TeacherRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeacherUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun getTeacher(
        page: Int,
    ): Flow<BaseNetworkResult<PeoplePagination>> {
        return teacherRepo.getTeacher(page = page)
    }
}