package com.alfayedoficial.coreNetwork.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 8/17/2022 - 2:24 AM
 */

@Module
@InstallIn(SingletonComponent::class)
object UrlDomain {

    init {
        System.loadLibrary("core-network")
    }

    @Provides
    @Singleton
    @Named("urlDomainOfDeployment")
    external fun provideUrlDomainOfDeployment(): String

    @Provides
    @Singleton
    @Named("apiKey")
    external fun provideApiKey(): String
}