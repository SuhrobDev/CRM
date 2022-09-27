package com.soul.crm.data.di.module

import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.repo.student.StudentRepoImpl
import com.soul.crm.data.repo.teacher.TeacherRepoImpl
import com.soul.crm.data.repo.user.UserRepoImpl
import com.soul.crm.domain.student.repo.StudentRepo
import com.soul.crm.domain.teacher.repo.TeacherRepo
import com.soul.crm.domain.user.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStudentRepository(mainService: ApiService): StudentRepo {
        return StudentRepoImpl(mainService)
    }

    @Provides
    fun provideUserRepository(mainService: ApiService): UserRepo {
        return UserRepoImpl(mainService)
    }

    @Provides
    fun provideTeacherRepository(mainService: ApiService): TeacherRepo {
        return TeacherRepoImpl(mainService)
    }
}