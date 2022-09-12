package com.seif.dictionarycomposeapp.data.local

import androidx.room.*
import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfoList(wordInfoList: List<WordInfoEntity>)

    @Query("DELETE FROM WordInfoEntity WHERE word IN(:words)")
    suspend fun deleteWordsInfoList(words: List<String>)


    @Query("SELECT * FROM WordInfoEntity WHERE word LIKE '%' || :word || '%'") //
    suspend fun getWordInfoList(word: String): List<WordInfoEntity>
}