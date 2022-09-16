package com.seif.dictionarycomposeapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.seif.dictionarycomposeapp.data.util.JsonParser
import com.seif.dictionarycomposeapp.domain.model.Meaning

@ProvidedTypeConverter // bec we want to provide our instance of this type converters bec by default type converters can't have constructor arguments
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json:String):List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }
    @TypeConverter
    fun toMeaningsJson(meanings:List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}