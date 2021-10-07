package com.me.job.tt.data.remote.request

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class MapRequest(
    val foundationId:String,
    val location:LatLng
):Serializable