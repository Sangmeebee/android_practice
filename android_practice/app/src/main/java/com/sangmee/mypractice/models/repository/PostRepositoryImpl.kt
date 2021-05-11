package com.sangmee.mypractice.models.repository

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.models.dataSource.PostRemoteDataSource
import io.reactivex.rxjava3.core.Observable

class PostRepositoryImpl(private val postRemoteDataSource: PostRemoteDataSource) : PostRepository {

    override fun getPosts(): Observable<List<Post>> {
        return postRemoteDataSource.getPosts()
    }

    override fun getComments(id: Int): Observable<List<Comment>> {
        return postRemoteDataSource.getComments(id)
    }
}
