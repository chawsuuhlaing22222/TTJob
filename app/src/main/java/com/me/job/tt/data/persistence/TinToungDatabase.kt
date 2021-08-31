package com.android.tintoung.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.me.job.tt.data.vos.RecentSearchVO


@Database(
        entities = [RecentSearchVO::class], version = 1, exportSchema = false
)
abstract class TinToungDatabase : RoomDatabase() {

    abstract fun recentDao(): RecentSearchDao

    companion object {

        private const val DB_NAME = "tt.db"
        private var INSTANCE: TinToungDatabase? = null

        fun getDatabase(context: Context): TinToungDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, TinToungDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                        .build()
            }
            return INSTANCE!!
        }
    }
}