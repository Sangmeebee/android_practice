package com.sangmee.mypractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.mypractice.databinding.ActivityMainBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { RecyclerAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.rvPostList.apply {
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
