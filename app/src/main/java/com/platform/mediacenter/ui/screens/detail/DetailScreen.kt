package com.platform.mediacenter.ui.screens.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.detail.SeasonResponse
import com.platform.mediacenter.data.model.detail.VideoResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.home.PopularStarsItemBox
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.ui.theme.blackWindowLight
import com.platform.mediacenter.utils.Constants.ACTIVE_PLAN
import com.platform.mediacenter.viewmodel.DataStoreViewModel
import com.platform.mediacenter.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    viewModel: DetailViewModel = hiltViewModel(),
    videoId: String?,
    type: String?
) {

    val context = LocalContext.current
    var isSuccess by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    val isLiked by viewModel.isLiked.collectAsState()
    val singleDetailsResult by viewModel.singleDetails.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSingleDetails(type, videoId)
        viewModel.getFavoriteStatus(videoId)


        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    when (singleDetailsResult) {
        is NetworkResult.Success -> {
            Log.e("4718", "DetailScreen Success : ${singleDetailsResult.message} ")
            isSuccess = true
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("4718", "DetailScreen Error : ${singleDetailsResult.message} ")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    var bottomSheetContentState by remember { mutableStateOf("") }
    var seasonNum by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetState = modalState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(vertical = 5.dp, horizontal = 100.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                when (bottomSheetContentState) {
                    "episode" -> {
                        if (singleDetailsResult.data?.season != null) {

                            singleDetailsResult.data?.let {
                                SeasonDropdownMenu(it.season!!) { index ->
                                    seasonNum = index
                                }
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 95.dp),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(
                                        it.season[seasonNum].episodes ?: emptyList()
                                    ) { episodeResponse ->
                                        MovieItemBox(
                                            height = 225,
                                            imageHeight = 180,
                                            image = rememberAsyncImagePainter(episodeResponse.imageUrl),
                                            name = episodeResponse.episodesName,
                                            year = "",
                                            quality = "",
                                            imdbRate = "",
                                            videoDescadd = ""
                                        ) {
                                            // episodeResponse.fileUrl
                                            // Go To Player
                                            navController.navigate(
                                                Screen.Player.withArg(
                                                    Uri.encode(
                                                        episodeResponse.fileUrl
                                                    )
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    "download" -> {
                        if (singleDetailsResult.data?.season != null) {

                            singleDetailsResult.data?.let {
                                SeasonDropdownMenu(it.season!!) { index ->
                                    seasonNum = index
                                }
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(1),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 105.dp),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(
                                        it.season[seasonNum].episodes ?: emptyList()
                                    ) { episodeResponse ->
                                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                            // todo download
                                        }) {
                                            Text(text = episodeResponse.episodesName, maxLines = 1)
                                        }
                                    }
                                }
                            }
                        } else {
                            singleDetailsResult.data?.let {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(1),
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(it.videos?.size!!) { index ->
                                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                            // todo download
                                        }) {
                                            Text(text = it.videos[index].label, maxLines = 1)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    "comment" -> {
                        val commentsResult by viewModel.comments.collectAsState()
                        if (commentsResult is NetworkResult.Success) {
                            val commentsList = commentsResult.data
                            commentsList.let { comments ->

                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(1),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(comments ?: emptyList()) { item ->

                                        item?.comments?.let {
                                            Row(Modifier.padding(vertical = 20.dp)) {
                                                Text(
                                                    text = item.userName
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))

                                                Text(
                                                    text = it
                                                )
                                            }

                                        }

                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    )
    {
        if (isSuccess) {

            singleDetailsResult.data?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    // Poster
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {

                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = rememberAsyncImagePainter(it.posterUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            blackWindowLight,
                                            Color.Transparent,
                                            Color.Transparent
                                        ),
                                        startY = Float.POSITIVE_INFINITY,
                                        endY = 0f
                                    )
                                )
                                .padding(top = 25.dp)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                }
                                .padding(30.dp)
                                .align(Alignment.TopEnd)
                                .rotate(180f),
                            tint = Color.Black
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = it.title,
                                modifier = Modifier
                                    .padding(vertical = 5.dp),
                                style = MaterialTheme.typography.h1,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = it.secondaryTitle,
                                modifier = Modifier
                                    .padding(vertical = 5.dp),
                                style = MaterialTheme.typography.h4,
                                color = Color.White
                            )
                            Text(
                                text = it.runtime,
                                modifier = Modifier
                                    .padding(vertical = 5.dp),
                                style = MaterialTheme.typography.h5,
                                color = Color.White
                            )

                        }
                    }

                    // Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {


                        if (it.isTvseries == "0") {

                            ServerDialog(it.videos!!) { serverItemClick ->

                                navController.navigate(
                                    Screen.Player.withArg(
                                        Uri.encode(
                                            serverItemClick
                                        )
                                    )
                                )
                            }
                        }
                        // Watch Button
                        Button({
                            if (ACTIVE_PLAN && dataStoreViewModel.getUserLoginStatus()) {
                                if (it.isTvseries == "1") {
                                    navController.navigate(
                                        Screen.Player.withArg(
                                            Uri.encode(
                                                it.season?.get(
                                                    0
                                                )?.episodes?.get(0)?.fileUrl
                                            )
                                        )
                                    )
                                } else {
                                    if (it.videos?.size!! > 1) {
                                        openServerDialogState.value = true
                                    } else {
                                        navController.navigate(
                                            Screen.Player.withArg(
                                                Uri.encode(it.videos[0].fileUrl)
                                            )
                                        )
                                    }
                                }
                            }
                            if (!dataStoreViewModel.getUserLoginStatus()) {
                                navController.navigate(Screen.StartLogin.route)
                            }

                            if (!ACTIVE_PLAN && dataStoreViewModel.getUserLoginStatus()) {
                                navController.navigate(Screen.PurchasePlan.route)
                            }
                        }) {

                            Text(
                                modifier = Modifier.padding(vertical = 2.dp, horizontal = 16.dp),
                                text =
                                if (!dataStoreViewModel.getUserLoginStatus()) {
                                    stringResource(R.string.login_register)
                                } else if (!ACTIVE_PLAN && it.isPaid == "1") {
                                    "تهیه اشتراک"
                                } else {
                                    if (it.isTvseries == "1") {
                                        "تماشای قسمت اول"
                                    } else {
                                        "تماشا"
                                    }
                                },
                                style = MaterialTheme.typography.h4,
                            )

                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        // Single Payment
                        if (dataStoreViewModel.getUserLoginStatus() && it.isPaid == "1" && !ACTIVE_PLAN) {
                            Button({
                                navController.navigate(Screen.PurchasePlan.route)
                            }) {
                                Text(
                                    text = "خرید تکی",
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }


                    Row {
                        // Preview
                        Button(
                            onClick = {
                                navController.navigate(
                                    Screen.Player.withArg(Uri.encode(it.trailerUrl))
                                )
                            },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text(
                                text = "پیش نمایش",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
                                color = Color.Black
                            )
                        }


                        // Download
                        Button(
                            onClick = {
                                scope.launch {
                                    bottomSheetContentState = "download"
                                    modalState.show()
                                }
                            },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text(
                                text = "دانلود",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
                                color = Color.Black
                            )
                        }


                        // Download
                        Button(
                            onClick = {
                                scope.launch {
                                    viewModel.getAllComments(it.videosId)
                                    bottomSheetContentState = "comment"
                                    modalState.show()
                                }
                            },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text(
                                text = "کامنت ها",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
                                color = Color.Black
                            )
                        }
                    }

                    // Circle Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .size(40.dp)
                                .background(Color.LightGray)
                                .clickable {

                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter =
                                if (it.userRating == "5") {
                                    painterResource(R.drawable.like_full)
                                } else {
                                    painterResource(R.drawable.like_svgrepo_com)
                                },
                                contentDescription = null
                            )

                        }

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .size(40.dp)
                                .background(Color.LightGray)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                modifier = Modifier.graphicsLayer(rotationZ = 180f),
                                painter =
                                if (it.userRating == "1") {
                                    painterResource(R.drawable.like_full)
                                } else {
                                    painterResource(R.drawable.like_svgrepo_com)
                                },
                                contentDescription = null
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .size(40.dp)
                                .background(Color.LightGray)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                imageVector = Icons.Filled.Share,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .size(40.dp)
                                .background(Color.LightGray)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                painter = painterResource(R.drawable.outline_outlined_flag_24),
                                contentDescription = null
                            )
                        }
                        if (dataStoreViewModel.getUserLoginStatus()) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .clip(CircleShape)
                                    .size(40.dp)
                                    .background(Color.LightGray)
                                    .clickable {
                                        viewModel.toggleLike(it.videosId)
                                        viewModel.getFavoriteStatus(it.videosId)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }
                    }

                    // Genre List
                    if (!it.genre.isNullOrEmpty()) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            items(it.genre.size) { index ->
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .clip(CircleShape)
                                        .background(Color.Gray)
                                        .padding(horizontal = 15.dp),
                                    contentAlignment = Alignment.Center

                                ) {
                                    Text(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        text = it.genre[index].name,
                                        color = Color.White,
                                        style = MaterialTheme.typography.h5
                                    )
                                }
                            }
                        }

                    }

                    // Percent Rating
                    Text(modifier = Modifier.padding(horizontal = 12.dp), text = "%${it.rating}")

                    // IMDB Rating
                    if (!it.imdbRating.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.imdb),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = it.imdbRating
                            )

                        }
                    }

                    // IMDB Ranking
                    if (!it.imdbRank.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFFDBA506),
                                            Color(0xFFF3DE7F),
                                            Color(0xFFDBA506)
                                        )
                                    )
                                )
                        )
                        {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.imdb),
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    text = if (it.isTvseries == "1") {
                                        "جزو ۲۵۰ سریال برتر IMDB با رتبه " + it.imdbRank
                                    } else {
                                        "جزو ۲۵۰ فیلم برتر IMDB با رتبه " + it.imdbRank
                                    },
                                    style = MaterialTheme.typography.h5,
                                    color = Color.Black
                                )

                            }

                        }

                    }

                    // Awards
                    if (!it.awards.isNullOrEmpty()) {
                        var expandState by remember { mutableStateOf(false) }
                        val rotationState by animateFloatAsState(targetValue = if (expandState) 180f else 0f)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(
                                    shape = RoundedCornerShape(10.dp),
                                    border = BorderStroke(1.dp, color = Color.Gray)
                                )
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 300
                                    )
                                ),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                expandState = !expandState
                            }
                        ) {

                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            modifier = Modifier.size(30.dp),
                                            painter = painterResource(R.drawable.ic_oscars),
                                            contentDescription = null
                                        )
                                        Text(
                                            text = "جوایز دریافتی",
                                            modifier = Modifier.padding(vertical = 5.dp),
                                            style = MaterialTheme.typography.h5,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    IconButton(modifier =
                                    Modifier
                                        .alpha(ContentAlpha.medium)
                                        .rotate(rotationState), onClick = {
                                        expandState = !expandState
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowDropDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                                if (expandState)
                                    Text(modifier = Modifier.padding(8.dp), text = it.awards)
                            }
                        }
                    }

                    // Dubbing
                    if (!it.dubbings.isNullOrEmpty()) {
                        FlowColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .wrapContentHeight()
                        ) {
                            it.dubbings.forEachIndexed { index, item ->
                                var expandStateDubbing by remember { mutableStateOf(false) }
                                val rotationStateDubbing by animateFloatAsState(targetValue = if (expandStateDubbing) 180f else 0f)

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()

                                        .border(
                                            shape = RoundedCornerShape(10.dp),
                                            border = BorderStroke(1.dp, color = Color.Gray)
                                        )
                                        .animateContentSize(
                                            animationSpec = tween(
                                                durationMillis = 300
                                            )
                                        ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {

                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    expandStateDubbing = !expandStateDubbing
                                                }
                                                .padding(8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Image(
                                                    modifier = Modifier.size(30.dp),
                                                    painter = painterResource(R.drawable.ic_microphone),
                                                    contentDescription = null
                                                )
                                                Text(
                                                    text = "عوامل دوبله",
                                                    modifier = Modifier.padding(vertical = 5.dp),
                                                    style = MaterialTheme.typography.h5,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                if (it.dubbings[index].studio.isNotEmpty()) {
                                                    Text(
                                                        text = " استدیو ${it.dubbings[index].studio}",
                                                        modifier = Modifier.padding(vertical = 5.dp),
                                                        style = MaterialTheme.typography.h5,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }

                                            }
                                            IconButton(modifier =
                                            Modifier
                                                .alpha(ContentAlpha.medium)
                                                .rotate(rotationStateDubbing), onClick = {
                                                expandStateDubbing = !expandStateDubbing
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowDropDown,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                        if (expandStateDubbing) {
                                            Column {
                                                Text(
                                                    modifier = Modifier.padding(8.dp),
                                                    text = it.dubbings[index].name
                                                )

                                                if (it.dubbings[index].dubbingManagers.isNotEmpty()) {
                                                    Text(
                                                        style = MaterialTheme.typography.h3,
                                                        modifier = Modifier.padding(8.dp),
                                                        text = "سرپرست گویندگان"
                                                    )

                                                    LazyRow(modifier = Modifier.padding(8.dp)) {
                                                        items(it.dubbings[index].dubbingManagers.size) { itemDubbingManagers ->
                                                            PopularStarsItemBox(
                                                                title = it.dubbings[index].dubbingManagers[itemDubbingManagers].name,
                                                                image = rememberAsyncImagePainter(it.dubbings[index].dubbingManagers[itemDubbingManagers].imageUrl)
                                                            ) {

                                                            }
                                                        }
                                                    }
                                                }

                                                if (it.dubbings[index].translators.isNotEmpty()) {
                                                    Text(
                                                        style = MaterialTheme.typography.h3,
                                                        modifier = Modifier.padding(8.dp),
                                                        text = "مترجمان"
                                                    )
                                                    LazyRow(modifier = Modifier.padding(8.dp)) {
                                                        items(it.dubbings[index].translators.size) { itemDubbingManagers ->
                                                            PopularStarsItemBox(
                                                                title = it.dubbings[index].translators[itemDubbingManagers].name,
                                                                image = rememberAsyncImagePainter(it.dubbings[index].translators[itemDubbingManagers].imageUrl)
                                                            ) {

                                                            }
                                                        }
                                                    }
                                                }

                                                if (it.dubbings[index].speakers.isNotEmpty()) {
                                                    Text(
                                                        style = MaterialTheme.typography.h3,
                                                        modifier = Modifier.padding(8.dp),
                                                        text = "گویندگان"
                                                    )

                                                    LazyRow(modifier = Modifier.padding(8.dp)) {
                                                        items(it.dubbings[index].speakers.size) { itemDubbingManagers ->
                                                            PopularStarsItemBox(
                                                                title = it.dubbings[index].speakers[itemDubbingManagers].name,
                                                                image = rememberAsyncImagePainter(it.dubbings[index].speakers[itemDubbingManagers].imageUrl)
                                                            ) {

                                                            }
                                                        }
                                                    }
                                                }

                                                if (it.dubbings[index].voiceRecordists.isNotEmpty()) {
                                                    Text(
                                                        style = MaterialTheme.typography.h3,
                                                        modifier = Modifier.padding(8.dp),
                                                        text = "ضبط"
                                                    )
                                                    LazyRow(modifier = Modifier.padding(8.dp)) {
                                                        items(it.dubbings[index].voiceRecordists.size) { itemDubbingManagers ->
                                                            PopularStarsItemBox(
                                                                title = it.dubbings[index].voiceRecordists[itemDubbingManagers].name,
                                                                image = rememberAsyncImagePainter(it.dubbings[index].voiceRecordists[itemDubbingManagers].imageUrl)
                                                            ) {

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                if (it.dubbings.size > 1)
                                    Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                        // Dubbing Count
                        if (it.dubbings.size > 1) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = String.format(
                                    "هر %d دوبله بر روی سریال قرار گرفت",
                                    it.dubbings.size
                                )
                            )
                        }
                    }

                    // Season
                    if (!it.season.isNullOrEmpty()) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, color = Color.Gray)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                scope.launch {
                                    bottomSheetContentState = "episode"
                                    modalState.show()
                                }
                            }
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .alpha(ContentAlpha.medium)
                                        .padding(10.dp),
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = null
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        modifier = Modifier.size(30.dp),
                                        painter = painterResource(R.drawable.ic_seasons_episodes),
                                        contentDescription = null
                                    )
                                    Text("فصل ها و قسمت ها")
                                }
                            }
                        }
                    }

                    // Description
                    if (it.description.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = it.description.replace("<br>", "\n"),
                            style = MaterialTheme.typography.h5
                        )
                    }

                    // Release Day
                    if (it.isTvseries == "1") {

                        it.broadcastDay?.let { broadcastDay ->
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "روز پخش :",
                                    style = MaterialTheme.typography.h5
                                )
                                Text(
                                    text = broadcastDay,
                                    style = MaterialTheme.typography.h5
                                )
                            }

                        }
                    }

                    // Director
                    if (!it.director.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("کارگردان :")
                            LazyRow {
                                items(it.director.size) { item ->
                                    PopularStarsItemBox(
                                        title = it.director[item].name,
                                        image = rememberAsyncImagePainter(it.director[item].imageUrl)
                                    ) {
                                        navController.navigate(Screen.MoreItem.route)
                                    }
                                }
                            }
                        }
                    }

                    // Writer
                    if (!it.writer.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("نویسنده :")
                            LazyRow {
                                items(it.writer.size) { item ->
                                    PopularStarsItemBox(
                                        title = it.writer[item].name,
                                        image = rememberAsyncImagePainter(it.writer[item].imageUrl)
                                    ) {
                                        navController.navigate(Screen.MoreItem.route)
                                    }
                                }
                            }
                        }
                    }

                    // Country
                    if (!it.country.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("کشور سازنده :")
                            it.country.forEachIndexed { index, item ->

                                var strCountry = ""
                                if (index == it.country.size - 1) {
                                    strCountry += item.name
                                } else {
                                    strCountry = (strCountry + item.name) + ", "
                                }

                                Text(text = strCountry)

                            }
                        }
                    }

                    // Release
                    if (!it.release.isNullOrEmpty()) {
                        Text(modifier = Modifier.padding(8.dp), text = " انتشار در ${it.release}")
                    }

                    // Cast And Crew
                    if (!it.castAndCrew.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("بازیگران و عوامل سازنده :")
                            LazyRow {
                                items(it.castAndCrew.size) { item ->
                                    PopularStarsItemBox(
                                        title = it.castAndCrew[item].name,
                                        image = rememberAsyncImagePainter(it.castAndCrew[item].imageUrl)
                                    ) {
                                        navController.navigate(Screen.MoreItem.route)
                                    }
                                }
                            }
                        }
                    }

                    // Related Movie
                    if (!it.relatedMovie.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("ممکن است دوست داشته باشید")
                            LazyRow {
                                items(it.relatedMovie.size) { item ->
                                    val relatedMovie = it.relatedMovie[item]
                                    MovieItemBox(
                                        image = rememberAsyncImagePainter(relatedMovie.posterUrl),
                                        name = relatedMovie.title,
                                        year = relatedMovie.release,
                                        quality = relatedMovie.videoQuality,
                                        imdbRate = relatedMovie.imdbRating,
                                        videoDescadd = relatedMovie.videoDescadd

                                    ) {
                                        navController.navigate(
                                            Screen.Detail.withArg(
                                                it.relatedMovie[item].videosId,type!!
                                            )
                                        )


                                    }
                                }
                            }
                        }
                    }

                    // Related Tv Series
                    if (!it.relatedTvseries.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("ممکن است دوست داشته باشید")
                            LazyRow {
                                items(it.relatedTvseries.size) { item ->
                                    val relatedMovie = it.relatedTvseries[item]
                                    MovieItemBox(
                                        image = rememberAsyncImagePainter(relatedMovie.posterUrl),
                                        name = relatedMovie.title,
                                        year = relatedMovie.release,
                                        quality = relatedMovie.videoQuality,
                                        imdbRate = relatedMovie.imdbRating,
                                        videoDescadd = relatedMovie.videoDescadd

                                    ) {
                                        navController.navigate(
                                            Screen.Detail.withArg(
                                                it.relatedTvseries[item].videosId,type!!
                                            )
                                        )

                                    }
                                }
                            }
                        }
                    }

                    // Comments

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeasonDropdownMenu(
    seasons: List<SeasonResponse>,
    onSeasonSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedSeason by remember { mutableStateOf(seasons[0].seasonsName) }

    Column {

        Box(modifier = Modifier.padding(16.dp)) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedSeason,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }, // این بخش جایگزین menuAnchor() شده است
                    label = { Text("فصل‌ ها", style = MaterialTheme.typography.h5) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    seasons.forEachIndexed { index, season ->
                        DropdownMenuItem(
                            text = { Text(season.seasonsName) },
                            onClick = {
                                selectedSeason = season.seasonsName
                                expanded = false
                                onSeasonSelected(index)
                            }
                        )
                    }
                }
            }
        }
    }

}

val openServerDialogState = mutableStateOf(false)

@Composable
fun ServerDialog(
    server: List<VideoResponse>,
    onEpisodeClick: (String) -> Unit
) {
    val openDialog = remember { openServerDialogState }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {

                Text(
                    text = "انتخاب سرور",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },
            text = {
                Column(modifier = Modifier.padding(vertical = 10.dp)) {

                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )

                    server.forEach { server ->
                        Button(
                            onClick = {
                                onEpisodeClick(server.fileUrl)
                                openDialog.value = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
//                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = server.label, textAlign = TextAlign.Center)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    onClick = { openDialog.value = false }) {
                    Text("بستن")
                }
            }
        )
    }

}
