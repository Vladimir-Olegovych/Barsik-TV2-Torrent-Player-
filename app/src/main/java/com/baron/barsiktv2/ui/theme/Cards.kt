package com.baron.barsiktv2.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import com.baron.barsiktv2.R
import com.baron.barsiktv2.models.DataItem
import com.baron.domain.models.TorrentFile

@Composable
fun SearchCard(dataItem: DataItem, onClick: (DataItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(dataItem) }
            .padding(16.dp)
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth().background(GrayBlack2)) {
            AsyncImage(
                modifier = Modifier.height(250.dp).width(150.dp).background(Color.Black),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dataItem.dataResult.image)
                    .error(R.drawable.no_image)
                    .build(),
                contentDescription = null,
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Text(text = dataItem.searchResult.title, color = Color.White, fontSize = 20.sp)
                Text(text = "Date: ${dataItem.searchResult.date}", color = Color.White, fontSize = 16.sp)
                Text(
                    modifier = Modifier,
                    text = "Source: ${dataItem.sourceSearch.sourceName}",
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Text(text = dataItem.dataResult.description ?: return, color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun TorrentCard(torrentFile: TorrentFile, onClick: (TorrentFile) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().background(Color.Gray).clickable {
        onClick.invoke(torrentFile)
    }) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(text = torrentFile.displayPath, color = Color.White)
        }
    }
}