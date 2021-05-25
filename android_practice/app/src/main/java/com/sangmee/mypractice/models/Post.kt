package com.sangmee.mypractice.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "post_table")
@Parcelize
data class Post(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    var comments: List<Comment>?
) : Parcelable
