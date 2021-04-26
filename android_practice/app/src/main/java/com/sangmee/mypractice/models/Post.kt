package com.sangmee.rxjavapractice.models

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val comments: List<Comment>?
)
