package com.seif.dictionarycomposeapp.data.local

import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: WordInfoDao
) {
    private suspend fun insertWordInfoList(wordInfoList: List<WordInfoEntity>) {
        dao.insertWordInfoList(wordInfoList)
    }

    suspend fun deleteWordsInfoList(words: List<String>) {
        dao.deleteWordsInfoList(words)
    }

    suspend fun getWordInfoList(word: String): List<WordInfoEntity> {
        return dao.getWordInfoList(word)
    }
    suspend fun offlineCacheRepositories(wordInfoList: List<WordInfoEntity>) {
        withContext(Dispatchers.IO) {
            deleteWordsInfoList(wordInfoList.map { it.word })
            insertWordInfoList(wordInfoList)
        }
    }

}