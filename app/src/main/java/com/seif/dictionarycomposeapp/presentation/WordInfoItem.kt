package com.seif.dictionarycomposeapp.presentation

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seif.dictionarycomposeapp.R
import com.seif.dictionarycomposeapp.domain.model.WordInfo

@Composable
fun WordInfoItem(
    modifier: Modifier = Modifier,
    wordInfo: WordInfo
) {
    val context = LocalContext.current
    Column(modifier = modifier) {
        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                text = wordInfo.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = R.drawable.ic_sound),
                contentDescription = "phonetic sound",
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .align(CenterVertically)
                    .padding(start = 8.dp)
                    .clickable {
                       MediaPlayer.create(context, Uri.parse(wordInfo.phonetics[0].audio)).start()
                    }
            )
        }

        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = Modifier.height(16.dp))

        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}