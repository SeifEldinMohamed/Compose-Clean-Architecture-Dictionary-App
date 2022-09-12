package com.seif.dictionarycomposeapp.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
) // we will add the phonetics at the end
