package com.me.job.tt.ui.delegate

import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse

interface EditCoverPhotoDelegate {
    fun onCoverPhotoClick(data: CoverPhotoUpdateDataResponse)
}