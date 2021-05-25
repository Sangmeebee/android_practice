package com.sangmee.mypractice.models.repository

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.models.dataSource.PostLocalDataSource
import com.sangmee.mypractice.models.dataSource.PostRemoteDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postLocalDataSource: PostLocalDataSource
) : PostRepository {

    override fun getPosts(): Observable<List<Post>> {
        return postRemoteDataSource.getPosts()
    }

    override fun getComments(id: Int): Observable<List<Comment>> {
        return postRemoteDataSource.getComments(id)
    }
}
