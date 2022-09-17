package com.seif.dictionarycomposeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seif.dictionarycomposeapp.domain.model.Meaning
import com.seif.dictionarycomposeapp.domain.model.Phonetic

@Entity
data class WordInfoEntity(
    @PrimaryKey
    val id: Int,
    val word:String,
    val phonetic: String?,
    val phonetics: List<Phonetic>,
    val meanings:List<Meaning>
)