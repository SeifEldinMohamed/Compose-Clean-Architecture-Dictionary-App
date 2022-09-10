package com.seif.dictionarycomposeapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("antonyms")
    val antonyms: List<Any>,
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
)