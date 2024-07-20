package com.madeean.testdansmultipro.presentation.job.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.madeean.testdansmultipro.R
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.util.ErrorItem
import com.madeean.testdansmultipro.presentation.util.ErrorView
import com.madeean.testdansmultipro.presentation.util.LoaderShow
import com.madeean.testdansmultipro.presentation.util.RequestState
import com.madeean.testdansmultipro.presentation.util.RequestState.Idle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobScreen(
  innerPaddingValues: PaddingValues,
  jobViewModel: JobViewModel,
  navigateToDetailScreen: (String) -> Unit
) {
  var textOnChanged by remember { mutableStateOf("") }
  var locationOnChanged by remember { mutableStateOf("") }
  var isShowFilter by remember { mutableStateOf(false) }
  var isFullTimeChecked by remember { mutableStateOf(true) }

  val listJob by rememberUpdatedState(jobViewModel.listJob.collectAsLazyPagingItems())

  LaunchedEffect(key1 = true) {
    jobViewModel.getListJob()
  }

  Column(
    modifier = Modifier
      .padding(innerPaddingValues)
      .fillMaxSize()
      .padding(top = 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row {
      OutlinedTextField(
        modifier = Modifier
          .padding(horizontal = 15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
          focusedBorderColor = Color.Black,
          unfocusedBorderColor = Color.Black,
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
          onSearch = {
             jobViewModel.getListJob(description = textOnChanged, location = locationOnChanged, full_time = isFullTimeChecked)
          }
        ),
        leadingIcon = {
          Icon(
            imageVector = Filled.Search,
            contentDescription = null
          )
        },
        shape = RoundedCornerShape(18.dp),
        placeholder = {
          Text("Search")
        },
        value = textOnChanged,
        onValueChange = {
          textOnChanged = it
        }
      )
      IconButton(onClick = { isShowFilter = !isShowFilter }) {
        if (isShowFilter) {
          Icon(imageVector = Filled.KeyboardArrowDown, contentDescription = null)
        } else {
          Icon(imageVector = Filled.KeyboardArrowUp, contentDescription = null)

        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    if (isShowFilter) {
      Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
          containerColor = Color.White
        ),
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp)
      ) {
        Column {
          Row(
            modifier = Modifier
              .padding(10.dp)
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text("Fulltime")
            Switch(checked = isFullTimeChecked, onCheckedChange = {
              isFullTimeChecked = it
            })
          }
          Row(
            modifier = Modifier
              .padding(10.dp)
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text("Location")
            OutlinedTextField(
              value = locationOnChanged,
              onValueChange = {
                locationOnChanged = it
              },
              modifier = Modifier.padding(start = 10.dp)
            )
          }
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp),
            horizontalArrangement = Arrangement.End
          ) {
            Button(onClick = {
              jobViewModel.getListJob(description = textOnChanged, location = locationOnChanged, full_time = isFullTimeChecked)
            }) {
              Text("Apply Filter")
            }
          }
        }
      }
    }

    LazyColumn {
      items(listJob.itemCount) { index ->
        val item = listJob[index]
        item?.let {
          ItemJob(it, navigateToDetailScreen)
        }
      }

      listJob.loadState.apply {
        when {
          refresh is LoadStateNotLoading && listJob.itemCount < 1 -> {
            item {
              Box(
                modifier = Modifier.fillMaxWidth(1f),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "No Items",
                  modifier = Modifier.align(Alignment.Center),
                  textAlign = TextAlign.Center
                )
              }
            }
          }

          refresh is LoadStateLoading -> {
            item {
              LoaderShow()
            }
          }

          append is LoadStateLoading -> {
            item {
              LoaderShow()
            }
          }

          refresh is LoadStateError -> {
            item {
              ErrorView(
                message = "No Internet Connection.",
                onClickRetry = { listJob.retry() },
                modifier = Modifier.fillMaxWidth(1f)
              )
            }
          }

          append is LoadStateError -> {
            item {
              ErrorItem(
                message = "No Internet Connection",
                onClickRetry = { listJob.retry() },
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun ItemJob(data: JobDomainModel, navigateToDetailScreen: (String) -> Unit) {
  Card(
    modifier = Modifier
      .padding(horizontal = 10.dp)
      .fillMaxWidth()
      .height(150.dp)
      .padding(vertical = 5.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(5.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    onClick = {
      navigateToDetailScreen(data.id)
    }
  ) {
    Row(
      modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.Top, // Align items at the top
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      AsyncImage(
        model = data.companyLogo,
        contentDescription = null,
        modifier = Modifier.size(120.dp),
        error = painterResource(
          id = R.drawable.indomie_goreng_png_0
        )
      )
      Spacer(modifier = Modifier.width(8.dp))

      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
      ) {
        Column(
          modifier = Modifier
            .align(Alignment.TopStart)
            .padding(end = 20.dp)
        ) {
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = data.title, color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(data.company, maxLines = 2, overflow = TextOverflow.Ellipsis)
          Spacer(modifier = Modifier.height(8.dp))
          Text(data.location, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
          contentDescription = null,
          modifier = Modifier
            .align(Alignment.CenterEnd)
            .size(24.dp)
        )
      }
    }
  }
}
