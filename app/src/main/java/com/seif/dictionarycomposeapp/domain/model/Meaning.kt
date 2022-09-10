package com.seif.dictionarycomposeapp.domain.model


data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
