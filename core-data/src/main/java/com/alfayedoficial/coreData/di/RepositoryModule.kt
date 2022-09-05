package com.alfayedoficial.coreData.di

import com.alfayedoficial.coreModel.domain.repo.ApiMainRepository
import com.alfayedoficial.coreData.repo.ApiMainRepositoryImp
import com.alfayedoficial.coreData.repo.DBMainRepositoryImp
import com.alfayedoficial.coreModel.domain.repo.DBMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:23 AM
 */

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideApiAuthRepository(repo : ApiMainRepositoryImp): ApiMainRepository {
        return repo
    }

    @Provides
    fun provideDBAuthRepository(repo : DBMainRepositoryImp): DBMainRepository {
        return repo
    }

}
