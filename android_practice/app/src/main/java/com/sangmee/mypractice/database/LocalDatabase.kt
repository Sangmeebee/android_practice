package com.sangmee.mypractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.service.CommentDao
import com.sangmee.mypractice.service.PostDao

@Database(entities = [Post::class, Comment::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {

        var instance: LocalDatabase? = null

        @Synchronized
        fun getInstance(context: Context): LocalDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, LocalDatabase::class.java, "sangmee_database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance
        }
    }
}
