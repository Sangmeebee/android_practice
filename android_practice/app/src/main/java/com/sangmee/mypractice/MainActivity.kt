package com.sangmee.mypractice

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sangmee.mypractice.databinding.ActivityMainBinding
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private val adapter by lazy { RecyclerAdapter(this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initViewModel()
        initRecyclerView()

    }

    private fun initViewModel() {

        vm.postsData.observe(this) { adapter.submitList(it) }
        vm.postData.observe(this) { updatePost(it) }
    }

    private fun updatePost(post: Post) {
        val oldList = adapter.currentList.toMutableList()
        with(oldList.indexOf(post)) {
            oldList[this] = post
            adapter.submitList(oldList)
        }
    }

    private fun initRecyclerView() {
        binding.rvPostList.apply {
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onClick(post: Post) {
        startActivity(Intent(this, SecondActivity::class.java).putExtra("post", post))
    }

    override fun onDestroy() {
        vm.unBindViewModel()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
