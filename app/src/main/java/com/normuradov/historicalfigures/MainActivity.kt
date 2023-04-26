package com.normuradov.historicalfigures

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.normuradov.historicalfigures.model.HistoricalFigure
import com.normuradov.historicalfigures.ui.theme.HistoricalFiguresTheme
import javax.sql.DataSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HistoricalFiguresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val figures = Repository.historicalFigures
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Historical Figures",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        })
    { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(figures) {
                HistoricalFigureListItem(figure = it)
            }
        }
    }

}

@Composable
fun HistoricalFigureListItem(figure: HistoricalFigure, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
//            .height(120.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = modifier.padding(8.dp)) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = figure.name, style = MaterialTheme.typography.displaySmall)
                Text(text = figure.shortDescription, style = MaterialTheme.typography.bodyLarge)
            }
            Image(
                painterResource(id = figure.avatarImageResourceId),
                contentDescription = null,
                modifier = modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightThemePreview() {
    HistoricalFiguresTheme(darkTheme = false) {
        App()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemePreview() {
    HistoricalFiguresTheme(darkTheme = true) {
        App()
    }
}