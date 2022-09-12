package com.seif.dictionarycomposeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seif.dictionarycomposeapp.domain.model.Meaning

@Entity
data class WordInfoEntity(
    @PrimaryKey
    val id: Int,
    val word:String,
    val phonetic: String,
    val meanings:List<Meaning>
)