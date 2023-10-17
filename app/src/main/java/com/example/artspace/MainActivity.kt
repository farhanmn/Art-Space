package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.data.Datasource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceComponent(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceComponent(modifier: Modifier) {
    var number by remember {
        mutableStateOf(0)
    }
    val arrayArt = Datasource().loadArtSpaces()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card (
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(arrayArt[number].artImage),
                        contentDescription = "desc",
                        modifier = Modifier
                            .padding(30.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Card (
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = LocalContext.current.getString(arrayArt[number].title),
                        modifier = Modifier
                            .padding(16.dp, 5.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 18.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(LocalContext.current.getString(arrayArt[number].artist))
                            }
                            append(" (" + LocalContext.current.getString(arrayArt[number].year) + ")")
                        },
                        modifier = Modifier
                            .padding(16.dp, 5.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "by Muhammad Farhan N - 23.541.1092",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(16.dp, 5.dp)
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                ActButton(text = "Previous") {
                    val arrLn = arrayArt.size
                    Log.d("TAG", "$number")
                    if (number > 0) {
                        number--
                    } else {
                        number = arrLn - 1
                    }
                }
                ActButton ("Next") {
                    val arrLn = arrayArt.size
                    Log.d("TAG", "$number")
                    if (number < arrLn - 1) {
                        number++
                    } else {
                        number = 0
                    }

                }
            }
        }
    }
}

@Composable
fun ActButton (text: String, function: () -> Unit ) {
    Button(
        modifier = Modifier
            .width(150.dp),
        onClick = function
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArtSpaceComponent(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}