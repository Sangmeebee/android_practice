package com.sangmee.mypractice.service

import androidx.room.Dao
import androidx.room.Query
import com.sangmee.mypractice.models.Post
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface PostDao : BaseRoomDao<Post> {

    @Query("SELECT * FROM post_table WHERE id=:id")
    fun getPost(id: Int): Single<Post>

    @Query("SELECT * FROM post_table")
    fun getAllPosts(): Maybe<List<Post>>

}
