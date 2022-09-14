package com.seif.dictionarycomposeapp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.seif.dictionarycomposeapp.common.Constants.Companion.BASE_URL
import com.seif.dictionarycomposeapp.data.local.Converters
import com.seif.dictionarycomposeapp.data.local.LocalDataSource
import com.seif.dictionarycomposeapp.data.local.WordInfoDatabase
import com.seif.dictionarycomposeapp.data.remote.DictionaryApi
import com.seif.dictionarycomposeapp.data.remote.RemoteDataSource
import com.seif.dictionarycomposeapp.data.repository.DictionaryRepositoryImp
import com.seif.dictionarycomposeapp.data.util.GsonParser
import com.seif.dictionarycomposeapp.domain.repository.DictionaryRepository
import com.seif.dictionarycomposeapp.domain.usecases.FetchWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WordInfoModule {

    @Provides
    @Singleton
    fun provideFetchWordInfoUseCase(repository: DictionaryRepository): FetchWordInfoUseCase {
        return FetchWordInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): DictionaryRepository {
        return DictionaryRepositoryImp(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: WordInfoDatabase): LocalDataSource {
        return LocalDataSource(db.dao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(dictionaryApi: DictionaryApi): RemoteDataSource {
        return RemoteDataSource(dictionaryApi)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}