package com.sangmee.mypractice.service

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Observable<List<Comment>>

}
