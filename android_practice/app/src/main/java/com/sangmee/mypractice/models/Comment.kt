package com.sangmee.mypractice.models

import java.io.Serializable

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
) : Serializable
