package com.seif.dictionarycomposeapp.presentation

import com.seif.dictionarycomposeapp.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems:List<WordInfo> = emptyList(),
    val isLoading:Boolean = false
)
