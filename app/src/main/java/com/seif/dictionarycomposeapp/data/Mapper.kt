package com.seif.dictionarycomposeapp.data

import com.seif.dictionarycomposeapp.data.local.entity.WordInfoEntity
import com.seif.dictionarycomposeapp.data.remote.dto.DefinitionDto
import com.seif.dictionarycomposeapp.data.remote.dto.MeaningDto
import com.seif.dictionarycomposeapp.data.remote.dto.PhoneticDto
import com.seif.dictionarycomposeapp.data.remote.dto.WordInfoDto
import com.seif.dictionarycomposeapp.domain.model.Definition
import com.seif.dictionarycomposeapp.domain.model.Meaning
import com.seif.dictionarycomposeapp.domain.model.Phonetic
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
        word = word,
        phonetics = phonetics.map { it.toPhonetic() }
    )
}

fun WordInfoDto.toWordInfoEntity(): WordInfoEntity {
    return WordInfoEntity(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        id = 0,
        word = word,
        phonetics = phonetics.map { it.toPhonetic() }
    )
}
fun PhoneticDto.toPhonetic(): Phonetic {
    return Phonetic(
        audio = audio,
        sourceUrl = sourceUrl
    )
}


fun WordInfoEntity.toWordInfo(): WordInfo {
    return WordInfo(
        meanings = meanings,
        phonetic = phonetic ?: "",
        word = word,
        phonetics = phonetics
    )
}