package com.soul.crm.domain.teacher.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import kotlinx.coroutines.flow.Flow

interface TeacherRepo {
    suspend fun getTeacher(page: Int): Flow<BaseNetworkResult<PeoplePagination>>
}