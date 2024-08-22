package com.inerviewapp.utils.di


import com.inerviewapp.repo.LoginRepo
import com.inerviewapp.repo.LoginRepoImpl
import com.inerviewapp.repo.images.ImagesRepo
import com.inerviewapp.repo.images.ImagesRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CustomModules {


    @Binds
    @Singleton
    abstract fun bindCartRepo(userRef: LoginRepoImpl): LoginRepo


    @Binds
    @Singleton
    abstract fun bindImagesRepo(userRef: ImagesRepoImpl): ImagesRepo
}