package com.seif.dictionarycomposeapp.data.remote

import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val dictionaryApi: DictionaryApi
) {
    suspend fun fetchForWordInfo(word:String): ArrayList<WordInfoDto> {
        return dictionaryApi.fetchForWordInfo(word)
    }
}