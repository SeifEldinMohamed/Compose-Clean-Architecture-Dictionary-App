package com.seif.dictionarycomposeapp.data.local.entity

import androidx.room.PrimaryKey
import com.seif.dictionarycomposeapp.domain.model.Meaning

data class WordInfoEntity(
    @PrimaryKey
    val id: Int,
    val word:String,
    val phonetic: String,
    val meanings:List<Meaning>
)