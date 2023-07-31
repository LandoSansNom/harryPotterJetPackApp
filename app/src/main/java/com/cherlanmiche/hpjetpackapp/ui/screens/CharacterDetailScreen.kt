package com.cherlanmiche.hpjetpackapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel

@Composable
fun CharacterDetailsScreen(character: CharacterModel) {

    Column(
        modifier = Modifier

    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            ImageFromUrl(
                imageUrl = character.image!!,
                contentDescription = "Image profile for ${character.actor}"
            )
        }

        Text(
            text = character.name!!, // Replace with character name
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )



        Text(
            text = character.alternateNames?.joinToString(separator = ", ")!!, // Replace with alternate names
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = character.actor!!, // Replace with actor name
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = character.gender!!, // Replace with gender
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = character.eyeColour!!, // Replace with eye color
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = character.hairColour!!, // Replace with hair color
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = character.dateOfBirth.toString(), // Replace with date of birth
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun ImageFromUrl(imageUrl: String, contentDescription: String?) {
    // Create a Painter from the image URL using Coil's rememberImagePainter
    val painter: Painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true) // Enable crossfade animation for smooth image loading
        }
    )

    // Display the image using the Image composable
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(5.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
    )
}

@Preview
@Composable
fun previewCharacterDetailsScreen() {
    CharacterDetailsScreen(CharacterModel(actor = "Cherlan-Miche", eyeColour = "Brown"))
}
