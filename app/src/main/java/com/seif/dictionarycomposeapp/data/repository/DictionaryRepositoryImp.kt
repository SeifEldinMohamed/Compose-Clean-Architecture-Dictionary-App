package com.seif.dictionarycomposeapp.data.repository

import com.seif.dictionarycomposeapp.common.Resource
import com.seif.dictionarycomposeapp.common.networkBoundResource
import com.seif.dictionarycomposeapp.data.local.LocalDataSource
import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.data.remote.RemoteDataSource
import com.seif.dictionarycomposeapp.data.toWordInfo
import com.seif.dictionarycomposeapp.data.toWordInfoEntity
import com.seif.dictionarycomposeapp.domain.model.WordInfo
import com.seif.dictionarycomposeapp.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DictionaryRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DictionaryRepository {
    override suspend fun fetchForWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        return networkBoundResource(
            query = {
                flow { localDataSource.getWordInfoList(word).map { it.toWordInfo() } }
            },
            fetch = {
                remoteDataSource.fetchForWordInfo(word)
            },
            saveFetchResult = {
                localDataSource.offlineCacheRepositories(it.map { wordInfoDto -> wordInfoDto.toWordInfoEntity() })
            },
            shouldFetch = {
                it.isEmpty()
            }
        )
    }


    override suspend fun getWordInfoList(word: String): List<WordInfoEntity> {
        return localDataSource.getWordInfoList(word)
    }

}

// HttpException: if we get invalid response
// IOException : incase of somthing went wrong with teh parsing or the server is not reachable or there is no internet connction