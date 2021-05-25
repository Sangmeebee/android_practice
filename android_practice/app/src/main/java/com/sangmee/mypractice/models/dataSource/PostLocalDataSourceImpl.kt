package com.sangmee.mypractice.models.dataSource

import com.sangmee.mypractice.database.LocalDatabase
import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(private val localDatabase: LocalDatabase) :
    PostLocalDataSource {

    override fun getAllPosts(): Maybe<List<Post>> {
        return localDatabase.postDao().getAllPosts()
    }

    override fun getPost(id: Int): Single<Post> {
        return localDatabase.postDao().getPost(id)
    }

    override fun getAllComments(): Maybe<List<Comment>> {
        return localDatabase.commentDao().getAllComments()
    }

    override fun getComment(id: Int): Single<Comment> {
        return localDatabase.commentDao().getComment(id)
    }
}
