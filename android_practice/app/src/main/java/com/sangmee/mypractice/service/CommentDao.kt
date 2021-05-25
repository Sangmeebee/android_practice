package com.sangmee.mypractice.service

import androidx.room.Dao
import androidx.room.Query
import com.sangmee.mypractice.models.Comment
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface CommentDao : BaseRoomDao<Comment> {

    @Query("SELECT * FROM comment_table WHERE id=:id")
    fun getComment(id: Int): Single<Comment>

    @Query("SELECT * FROM comment_table")
    fun getAllComments(): Maybe<List<Comment>>

}
