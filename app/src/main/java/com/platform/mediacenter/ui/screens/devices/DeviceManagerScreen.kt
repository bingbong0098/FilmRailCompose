package com.platform.mediacenter.ui.screens.devices

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.platform.mediacenter.data.model.devices.DeviceResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.viewmodel.DevicesViewModel

@Composable
fun DeviceManagerScreen(navController: NavHostController,viewModel: DevicesViewModel = hiltViewModel()) {
    LaunchedEffect (true){
        viewModel.getAllDevices()
    }
    val devicesResult by viewModel.allDevices.collectAsState()
    var devicesList by remember {
        mutableStateOf<List<DeviceResponse.Device>?>(emptyList())
    }

    if (devicesResult is NetworkResult.Success){
        devicesList = devicesResult.data?.data?.devices
        LazyColumn (
            modifier = Modifier.fillMaxSize().padding(vertical = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            devicesList?.size?.let {
                items(it){ index ->
                    DeviceItemBox(devicesList!![index].deviceName!!)
                }
            }
        }
    }else{
        Log.e("4718", "DeviceManagerScreen: ${devicesResult.message}" )
    }
    Icon(
        imageVector = Icons.Filled.ArrowBackIos,
        contentDescription = null,
        modifier = Modifier
            .clickable {
                navController.popBackStack()
            }
            .padding(30.dp)
    )
}