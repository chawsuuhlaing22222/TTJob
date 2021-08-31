package com.android.tintoung.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.me.job.tt.data.vos.RecentSearchVO
import com.me.job.tt.utils.AppConstants


@Dao
abstract interface RecentSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRecent(recent: RecentSearchVO): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRecents(recents: List<RecentSearchVO>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_RECENT_SEARCH} ORDER BY recentSearchId DESC")
    abstract fun getRecents(): List<RecentSearchVO>

    @Query("SELECT * FROM ${AppConstants.TABLE_RECENT_SEARCH} WHERE recentSearchId = :recentId")
    abstract fun getRecentById(recentId: String): RecentSearchVO?

    @Query("DELETE FROM ${AppConstants.TABLE_RECENT_SEARCH} WHERE recentSearchId = :recentId")
    abstract fun deleteRecentById(recentId: String): Int

}