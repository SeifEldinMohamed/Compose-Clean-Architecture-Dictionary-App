package com.seif.dictionarycomposeapp.domain.repository

import com.seif.dictionarycomposeapp.common.Resource
import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun fetchForWordInfo(word:String): Flow<Resource<List<WordInfo>>>


//    suspend fun insertWordInfo(wordInfoList: List<WordInfoEntity>)
//
//    suspend fun deleteWordsInfo(words: List<String>)

    suspend fun getWordInfoList(word: String): List<WordInfoEntity>
}