package com.project.recipeapplication.ui.components

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
/**
 * Composable function for displaying an image selection section.
 *
 * @param viewModel The view model containing the logic for managing personal recipes.
 */
@Composable
fun ImageSection(viewModel : PersonalRecipeViewModel) {
    val context = LocalContext.current
    val imageUri = viewModel.recipeImagePath

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.setImageUri(context, uri)
    }


    val requestPermissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allPermissionsGranted = permissions.entries.all { it.value }
        if (allPermissionsGranted) {
            launcher.launch("image/*")
        } else {
            println("No permissions granted")
        }
    }

    Button(
        onClick = {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                    requestPermissionsLauncher.launch(arrayOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                    ))
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    requestPermissionsLauncher.launch(arrayOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO
                    ))
                }
                else -> {
                    requestPermissionsLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                }
            }
        }
    ) {
        Text(text = "Select Image")
    }


    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxHeight()
            .width(140.dp)
            .aspectRatio(1f).border(1.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {

        imageUri?.let { uriString ->
            AsyncImage(
                model = Uri.parse(uriString),
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

    }









}