package com.me.job.tt.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName
import com.me.job.tt.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_RECENT_SEARCH)
class RecentSearchVO(
        @SerializedName("keywords")
        var keywords: String = "") {

        @PrimaryKey(autoGenerate = true)
        @SerializedName("recent_search_id")
        var recentSearchId: Int = 0

}