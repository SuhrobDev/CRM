package com.soul.crm.data.di.module

import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.repo.course.CourseRepoImpl
import com.soul.crm.data.repo.payment.PaymentRepoImpl
import com.soul.crm.data.repo.student.StudentRepoImpl
import com.soul.crm.data.repo.teacher.TeacherRepoImpl
import com.soul.crm.data.repo.user.UserRepoImpl
import com.soul.crm.domain.course.repo.CourseRepo
import com.soul.crm.domain.payment.repo.PaymentRepo
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

    @Provides
    fun providePaymentRepository(mainService: ApiService): PaymentRepo {
        return PaymentRepoImpl(mainService)
    }

    @Provides
    fun provideCourseRepository(mainService: ApiService): CourseRepo {
        return CourseRepoImpl(mainService)
    }
}