package com.soul.crm.data.di.module

import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.repo.student.StudentRepoImpl
import com.soul.crm.domain.student.repo.StudentRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideServiceRepository(mainService: ApiService): StudentRepo {
        return StudentRepoImpl(mainService)
    }
}