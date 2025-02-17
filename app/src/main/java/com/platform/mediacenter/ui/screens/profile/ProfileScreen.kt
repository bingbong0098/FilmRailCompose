package com.platform.mediacenter.ui.screens.profile

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.DriveFolderUpload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.material.placeholder
import com.platform.mediacenter.R
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.utils.Constants.USER_ID
import com.platform.mediacenter.viewmodel.ProfileViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        viewModel.getUserData()
    }

    val userData by viewModel.userProfileData.collectAsState()
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "اجازه دسترسی به گالری داده نشد", Toast.LENGTH_SHORT).show()
        }
    }

    if (userData is NetworkResult.Success) {
        var nameTextState by remember { mutableStateOf(userData.data?.name.toString()) }
        var emailTextState by remember { mutableStateOf(userData.data?.email.toString()) }
        var phoneTextState by remember { mutableStateOf(userData.data?.phone.toString()) }
        var genderTextState by remember { mutableStateOf(userData.data?.gender.toString()) }
        var newPassTextState by remember { mutableStateOf("") }
        var passTextState by remember { mutableStateOf("") }


        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(100.dp)
            ) {
                AsyncImage(
                    model = selectedImageUri
                        ?: if (userData.data?.imageUrl.isNullOrEmpty()) R.drawable.ic_account_circle_black else userData.data?.imageUrl,
                    modifier = Modifier
                        .placeholder(visible = false)
                        .clip(CircleShape)
                        .clickable {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            } else {
                                galleryLauncher.launch("image/*")
                            }

                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(3.dp),
                    imageVector = Icons.Filled.DriveFolderUpload,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            OutlinedTextField(
                placeholder = { Text("نام") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { nameTextState = it },
                value = nameTextState
            )
            OutlinedTextField(
                placeholder = { Text("ایمیل") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { emailTextState = it },
                value = emailTextState
            )

            OutlinedTextField(
                placeholder = { Text("شماره موبایل") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { phoneTextState = it },
                value = phoneTextState
            )

            OutlinedTextField(
                placeholder = { Text("جنسیت") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { genderTextState = it },
                value = genderTextState
            )

            OutlinedTextField(
                placeholder = { Text("رمز ورود جدید (اختیاری)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { newPassTextState = it },
                value = newPassTextState
            )
            OutlinedTextField(
                placeholder = { Text("رمز ورود فعلی") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onValueChange = { passTextState = it },
                value = passTextState
            )
            Button(
                onClick = {
                    val name: RequestBody =
                        emailTextState.toRequestBody("text/plain".toMediaTypeOrNull())
                    val email: RequestBody =
                        nameTextState.toRequestBody("text/plain".toMediaTypeOrNull())
                    val phone: RequestBody =
                        phoneTextState.toRequestBody("text/plain".toMediaTypeOrNull())
                    val gender: RequestBody =
                        genderTextState.toRequestBody("text/plain".toMediaTypeOrNull())
                    val newPass: RequestBody =
                        newPassTextState.toRequestBody("text/plain".toMediaTypeOrNull())
                    val pass: RequestBody =
                        passTextState.toRequestBody("text/plain".toMediaTypeOrNull())

                    var multipartBody: MultipartBody.Part? = null
                    val userId: RequestBody =
                        USER_ID.toRequestBody("text/plain".toMediaTypeOrNull())
                    selectedImageUri?.let { uri ->
                        try {
                            val inputStream =
                                context.contentResolver.openInputStream(uri) ?: return@let
                            val file =
                                File.createTempFile("upload", ".jpg", context.cacheDir).apply {
                                    outputStream().use { output -> inputStream.copyTo(output) }
                                }

                            val requestFile =
                                file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                            multipartBody =
                                MultipartBody.Part.createFormData("photo", file.name, requestFile)

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                    viewModel.updateProfile(
                        name = name,
                        email = email,
                        phone = phone,
                        gender = gender,
                        password = newPass,
                        currentPassword = pass,
                        photo = multipartBody,
                        userId = userId
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = stringResource(R.string.register),
                    style = MaterialTheme.typography.h5
                )
            }
            Button(
                onClick = {

                    viewModel.deactivateAccount(passTextState,"")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Red
                )
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "غیرفعال سازی حساب",
                    style = MaterialTheme.typography.h5
                )
            }
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = "اگر نمیخواهید از این حساب استفاده کنید میتوانید به راحتی ان را غیرفعال کنید",
                color = Color.Gray,
                style = MaterialTheme.typography.h6
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .padding(30.dp)
                .rotate(180f),
            tint = Color.Gray
        )
    } else if (userData is NetworkResult.Error) {
        Log.e("4718", "ProfileScreen: ${userData.message}")
    }

}
