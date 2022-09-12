package com.seif.dictionarycomposeapp.data.local

import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: WordInfoDao
) {
    suspend fun insertWordInfo(wordInfoList: List<WordInfoEntity>) {
        dao.insertWordInfoList(wordInfoList)
    }

    suspend fun deleteWordsInfo(words: List<String>) {
        dao.deleteWordsInfoList(words)
    }

    suspend fun getWordInfoList(word: String): List<WordInfoEntity> {
        return dao.getWordInfoList(word)
    }
}