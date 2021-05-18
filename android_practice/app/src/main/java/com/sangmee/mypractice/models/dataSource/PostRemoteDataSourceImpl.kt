package com.sangmee.mypractice.models.dataSource

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.service.ServiceGenerator
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor() : PostRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return ServiceGenerator.getRequestApi().getPosts()
    }

    override fun getComments(id: Int): Observable<List<Comment>> {
        return ServiceGenerator.getRequestApi().getComments(id)
    }
}
