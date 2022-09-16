package com.seif.dictionarycomposeapp.data.repository

import com.seif.dictionarycomposeapp.common.Resource
import com.seif.dictionarycomposeapp.common.networkBoundResource
import com.seif.dictionarycomposeapp.data.local.LocalDataSource
import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.data.remote.RemoteDataSource
import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto
import com.seif.dictionarycomposeapp.data.toWordInfo
import com.seif.dictionarycomposeapp.data.toWordInfoEntity
import com.seif.dictionarycomposeapp.domain.model.WordInfo
import com.seif.dictionarycomposeapp.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class DictionaryRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DictionaryRepository {
    override fun fetchForWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
//        return networkBoundResource(
//            query = {
//                flow { localDataSource.getWordInfoList(word).map { it.toWordInfo() } }
//            },
//            fetch = {
//                remoteDataSource.fetchForWordInfo(word)
//            },
//            saveFetchResult = {
//                localDataSource.offlineCacheRepositories(it.map { wordInfoDto -> wordInfoDto.toWordInfoEntity() })
//            },
//            shouldFetch = {
//                it.isEmpty()
//            }
//        )
        emit(Resource.Loading())

        val wordInfos = localDataSource.getWordInfoList(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos: ArrayList<WordInfoDto> = remoteDataSource.fetchForWordInfo(word)
            //localDataSource.offlineCacheRepositories(remoteWordInfos.map { wordInfoDto -> wordInfoDto.toWordInfoEntity() })
            localDataSource.deleteWordsInfoList(remoteWordInfos.map { it.word })
            localDataSource.insertWordInfoList(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = wordInfos
                )
            )
        }

        val newWordInfos = localDataSource.getWordInfoList(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }


    override suspend fun getWordInfoList(word: String): List<WordInfoEntity> {
        return localDataSource.getWordInfoList(word)
    }

}

// HttpException: if we get invalid response
// IOException : incase of somthing went wrong with teh parsing or the server is not reachable or there is no internet connction