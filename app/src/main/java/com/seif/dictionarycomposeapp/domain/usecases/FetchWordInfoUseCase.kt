package com.seif.dictionarycomposeapp.domain.usecases

import com.seif.dictionarycomposeapp.common.Resource
import com.seif.dictionarycomposeapp.domain.model.WordInfo
import com.seif.dictionarycomposeapp.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchWordInfoUseCase(
    private val repository: DictionaryRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()){
            return flow {  }
        }
        return repository.fetchForWordInfo(word)
    }
}