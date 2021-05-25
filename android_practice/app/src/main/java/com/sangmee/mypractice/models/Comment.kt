package com.sangmee.mypractice.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "comment_table")
@Parcelize
data class Comment(
    @PrimaryKey
    val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
) : Parcelable
