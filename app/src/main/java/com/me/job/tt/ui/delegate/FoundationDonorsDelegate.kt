package com.android.tintoung.ui.delegates


import com.me.job.tt.data.remote.response.FoundationDonors


interface FoundationDonorsDelegate {
    fun onItemClick(data: FoundationDonors.Data)
    fun onAccessRejectClick(data: FoundationDonors.Data)
}