package com.sangmee.mypractice.models

import java.io.Serializable

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    var comments: List<Comment>?
) : Serializable
