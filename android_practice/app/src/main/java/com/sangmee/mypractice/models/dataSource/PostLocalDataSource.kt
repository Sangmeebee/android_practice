package com.sangmee.mypractice.models.dataSource

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface PostLocalDataSource {

    fun getAllPosts(): Maybe<List<Post>>
    fun getPost(id: Int): Single<Post>
    fun getAllComments(): Maybe<List<Comment>>
    fun getComment(id: Int): Single<Comment>
}
