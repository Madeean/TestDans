package com.madeean.testdansmultipro.presentation.job.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.madeean.testdansmultipro.R
import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.job.viewmodel.JobViewModel
import com.madeean.testdansmultipro.presentation.util.ErrorView
import com.madeean.testdansmultipro.presentation.util.LoaderShow
import com.madeean.testdansmultipro.presentation.util.RequestState
import com.madeean.testdansmultipro.presentation.util.RequestState.Idle
import com.madeean.testdansmultipro.presentation.util.RequestState.Loading
import com.madeean.testdansmultipro.presentation.util.RequestState.Success
import com.madeean.testdansmultipro.presentation.util.htmlToString


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJobScreen(idJob: String, viewModel: JobViewModel, navController: NavController) {
  val scrollState = rememberScrollState()
  val detailJob by viewModel.detailJob.collectAsState()

  LaunchedEffect(key1 = true) {
    viewModel.getDetailJob(idJob)
  }

  Scaffold(
    topBar = {
      Surface(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
      ) {
        CenterAlignedTopAppBar(
          colors = TopAppBarColors(
            containerColor = Color.LightGray,
            scrolledContainerColor = Color.LightGray,
            navigationIconContentColor = Color.DarkGray,
            titleContentColor = Color.DarkGray,
            actionIconContentColor = Color.DarkGray
          ),
          title = {
            Text(
              "Detail Job"
            )
          },
          navigationIcon = {
            IconButton(
              onClick = {
                navController.navigateUp()
              }
            ) {
              Icon(imageVector = Filled.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    },
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 20.dp)
        .verticalScroll(scrollState),
    ) {

      when (detailJob) {
        is Idle -> {
          LoaderShow()
        }

        is Loading -> {
          LoaderShow()
        }

        is Success -> {
          val data = (detailJob as Success<JobDomainModel>).data

          ContentDetailJob(data)
        }

        is RequestState.Error -> {
          ErrorView(
            message = (detailJob as RequestState.Error).error.message
              ?: "Halaman error silahkan cek koneksi anda dan coba lagi"
          ) {
            viewModel.getDetailJob(idJob)
          }
        }
      }

    }
  }
}

@Composable
fun ContentDetailJob(data: JobDomainModel) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(12.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    AsyncImage(
      model = data.companyLogo,
      contentDescription = null,
      modifier = Modifier.fillMaxWidth(),
      contentScale = ContentScale.Crop,
      error = painterResource(id = R.drawable.indomie_goreng_png_0)
    )
  }
  Spacer(modifier = Modifier.height(20.dp))
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier.padding(15.dp)
    ) {
      Text("titile", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text(data.title, fontSize = 20.sp, color = Color.Black)

      Text("type", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${data.type}", fontSize = 20.sp, color = Color.Black)

      Text("Company", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${data.company}", fontSize = 20.sp, color = Color.Black)

      Text("Location", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${data.location}", fontSize = 20.sp, color = Color.Black)

      Text("Created At", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${data.location}", fontSize = 20.sp, color = Color.Black)
    }
  }
  Spacer(modifier = Modifier.height(20.dp))

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        "Description",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )

      Text(
        text = htmlToString(data.description), modifier = Modifier.align(Alignment.Start),
        color = Color.Black,
        fontSize = 16.sp,
      )

    }
  }
  Spacer(modifier = Modifier.height(20.dp))

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        "How to Apply",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )

      Text(
        text = htmlToString(data.howToApply), modifier = Modifier.align(Alignment.Start),
        color = Color.Black,
        fontSize = 16.sp,
      )

    }
  }
  Spacer(modifier = Modifier.height(20.dp))
}