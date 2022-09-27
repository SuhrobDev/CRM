package com.soul.crm.domain.user.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.teacher.repo.TeacherRepo
import com.soul.crm.domain.user.repo.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepo: UserRepo) {
    suspend fun getUser(
        page: Int,
    ): Flow<BaseNetworkResult<PeoplePagination>> {
        return userRepo.getUser(page = page)
    }
}