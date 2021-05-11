package com.sangmee.mypractice.models.repository

import com.sangmee.mypractice.models.Comment
import com.sangmee.mypractice.models.Post
import io.reactivex.rxjava3.core.Observable

interface PostRepository {

    fun getPosts(): Observable<List<Post>>
    fun getComments(id: Int): Observable<List<Comment>>
}
