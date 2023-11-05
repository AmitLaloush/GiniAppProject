package com.laloushltd.giniappproject.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.laloushltd.giniappproject.domain.model.ImageData

@Composable
fun ImageItem(imageData: ImageData) {
    Column {

        AsyncImage(
            model = imageData.url,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Number of likes: ${imageData.likes}" ?: "",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,

        )
    }
}
