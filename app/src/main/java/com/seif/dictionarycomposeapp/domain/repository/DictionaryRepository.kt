package com.seif.dictionarycomposeapp.domain.repository

import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto

interface DictionaryRepository {

    suspend fun fetchForWordInfo(word:String): ArrayList<WordInfoDto>


    suspend fun insertWordInfo(wordInfoList: List<WordInfoEntity>)

    suspend fun deleteWordsInfo(words: List<String>)

    suspend fun getWordInfoList(word: String): List<WordInfoEntity>
}