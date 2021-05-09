package com.sangmee.mypractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.mypractice.databinding.ActivitySecondBinding
import com.sangmee.mypractice.models.Post

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<Post>("post")
        Log.d(TAG, post.toString())
    }


    companion object {
        private const val TAG = "SecondActivity"
    }
}
