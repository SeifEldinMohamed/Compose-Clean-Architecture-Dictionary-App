package com.seif.dictionarycomposeapp.data

import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.data.remote.dto.DefinitionDto
import com.seif.dictionarycomposeapp.data.remote.dto.MeaningDto
import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto
import com.seif.dictionarycomposeapp.domain.model.Definition
import com.seif.dictionarycomposeapp.domain.model.Meaning
import com.seif.dictionarycomposeapp.domain.model.WordInfo

fun DefinitionDto.toDefinition(): Definition {
    return Definition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )
}

fun MeaningDto.toMeaning(): Meaning {
    return Meaning(
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech
    )
}

fun WordInfoDto.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        word = word
    )
}

fun WordInfoEntity.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings,
        phonetic = phonetic,
        word = word
    )
}