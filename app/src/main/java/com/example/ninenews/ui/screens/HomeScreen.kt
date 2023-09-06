package com.example.ninenews.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ninenews.R
import com.example.ninenews.model.Assets
import com.example.ninenews.model.sortByImageWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(onItemClicked: (Assets) -> Unit, newsViewModel: NewsViewModel) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NewsTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.news_list)
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeScreen(
                retryAction = newsViewModel::getNewsList,
                newsUiState = newsViewModel.newsUiState,
                onItemClicked = onItemClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    title: String
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onItemClicked: (Assets) -> Unit,
) {
    when (newsUiState) {
        is NewsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is NewsUiState.Success -> NewsListScreen(
            assets = newsUiState.assets.toList(),
            modifier = modifier,
            onItemClicked
        )

        is NewsUiState.Error -> ErrorScreen(
            newsUiState.error,
            retryAction,
            modifier = modifier.fillMaxSize()
        )

        is NewsUiState.Empty -> EmptyScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button
 * in case of ane error in API handshake.
 */
@Composable
fun ErrorScreen(error: String, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.connection_svgrepo_com),
            contentDescription = stringResource(R.string.loading_failed)
        )
        Text(
            text = stringResource(R.string.loading_failed) + ":\n $error",
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * If list of the news is empty then
 * The home screen displaying EMPTY message with re-attempt button.
 */
@Composable
fun EmptyScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_svgrepo_com),
            contentDescription = stringResource(R.string.empty_list)
        )
        Text(text = stringResource(R.string.empty_list), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.try_again))
        }
    }
}

/**
 * Use activity and/or fragments where appropriate, but should be adaptable to all Phone screen sizes/rotation
 * LazyVerticalGrid will handle screen sizes. We only define minimum width here.
 * If there is more room(landscape or tablet) then it will show more columns
 */
@Composable
fun NewsListScreen(
    assets: List<Assets>,
    modifier: Modifier = Modifier,
    onItemClicked: (Assets) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(250.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = assets) {
            NewsCard(
                it,
                onItemClicked
            )
        }
    }
}

@Composable
fun NewsCard(asset: Assets, onItemClicked: (Assets) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        onClick = {
            onItemClicked(asset)
        }

    ) {
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
                .testTag(asset.timeStamp.toString()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //If there are related images available for an asset,
            // display the smallest image available for the asset in the cell.
            // we need to check width of images from the json
            val imageUrl = asset.sortByImageWidth().relatedImages.lastOrNull()

            Text(
                text = asset.headline ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3

            )
            Text(
                text = asset.theAbstract ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Text(
                text = asset.byLine ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            //Images should be loaded asynchronously and cached
            // Coil library works better than Glide with jetpack Compose
            AsyncImage(
                error = painterResource(R.drawable.broken_svgrepo_com),
                placeholder = painterResource(R.drawable.loading_svgrepo_com),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl?.url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.article_photo),
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
            )
        }
    }
}
