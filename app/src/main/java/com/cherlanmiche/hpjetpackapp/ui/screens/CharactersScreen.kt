package com.cherlanmiche.hpjetpackapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CharactersScreen(navController: NavHostController, viewModel: CharactersViewModel) {
    val characters by viewModel.characters.observeAsState(listOf())

    Column {
        LazyColumn {
            items(characters ?: listOf()) { character ->
                CharacterItem(character, navController) {
                    //navController.navigate("DetailsScreen/${character.id}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    character: CharacterModel,
    navController: NavHostController,
    onItemClick: () -> Unit
) {
    val context = LocalContext.current
    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    // LaunchedEffect launches a new coroutine every time the key (meal.strMealThumb) changes.
    LaunchedEffect(character.image) {
        // The image is loaded from a URL.
//        image = loadImage(context, meal.strMealThumb!!)
        character.image?.let {
            image = loadImage(context, it)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            image?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Image for ${character.actor}",
                    modifier = Modifier
                        .size(80.dp) // Set the size of the image slightly larger
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.actor!!, // Replace with your label text
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Add any other texts or UI elements here as needed
            }
        }
    }

}

// This function loads an image from a URL using the Glide library.
// It's a suspending function, so it can be called from a coroutine scope without blocking the main thread.
suspend fun loadImage(context: Context, url: String): ImageBitmap? {
    // Dispatchers.IO is used for network and disk operations.
    return withContext(Dispatchers.IO) {
        val glide = Glide.with(context)
        val futureTarget: FutureTarget<Bitmap> = glide
            .asBitmap()
            .load(url)
            .submit()

        try {
            // The image is loaded and converted to an ImageBitmap.
            val bitmap: Bitmap = futureTarget.get()
            glide.clear(futureTarget)
            bitmap.asImageBitmap()
        } catch (e: Exception) {
            // If the image cannot be loaded, null is returned.
            null
        }
    }


}