package com.seif.dictionarycomposeapp.data.remote

import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun fetchForWordInfo(@Path("word") word:String): ArrayList<WordInfoDto>
}