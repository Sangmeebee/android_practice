package com.sangmee.mypractice.models.dataSource

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.service.RequestApi
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(private val requestApi: RequestApi) :
    PostRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return requestApi.getPosts()
    }

    override fun getComments(id: Int): Observable<List<Comment>> {
        return requestApi.getComments(id)
    }
}
