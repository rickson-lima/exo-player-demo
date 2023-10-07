package com.example.exosample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Player() {
    val mp3 =  "https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"
    val mp4 =  "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
    val mp3Api = "http://192.168.24.67:3000/"
    val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
    val mediaSource = ProgressiveMediaSource
        .Factory(dataSourceFactory)
        .createMediaSource(
            MediaItem.fromUri(mp3Api)
        )

    val playerListener = object: Player.Listener {}

    val player = ExoPlayer.Builder(LocalContext.current)
        .build()
        .apply {
            setMediaSource(mediaSource)
            prepare()
            addListener(playerListener)
            play()
        }

    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            player.release()
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 58.dp, vertical = 36.dp)
                .background(MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = "Sample",
                style = MaterialTheme.typography.headlineLarge,
            )

            Row {
                Button(
                    onClick = {
                        player.play()
                    }
                ) {
                    Text(text = "Play")
                }

                Button(
                    onClick = {
                        player.pause()
                    }
                ) {
                    Text(text = "Pause")
                }

                Button(
                    onClick = {
                        player.pause()
                    }
                ) {
                    Text(text = "Stop")
                }
            }
        }

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.marilia),
                contentDescription = null,
            )
        }
    }
}