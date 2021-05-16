package com.sangmee.mypractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sangmee.mypractice.databinding.ActivityMainBinding
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.models.dataSource.PostRemoteDataSourceImpl
import com.sangmee.mypractice.models.repository.PostRepository
import com.sangmee.mypractice.models.repository.PostRepositoryImpl

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private val adapter by lazy { RecyclerAdapter(this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(
            this,
            MainViewModelFactory(PostRepositoryImpl(PostRemoteDataSourceImpl()))
        ).get(MainViewModel::class.java)

        initViewModel()
        initRecyclerView()

    }

    private fun initViewModel() {

        vm.postsData.observe(this) { adapter.submitList(it) }
        vm.postData.observe(this) { updatePost(it) }
    }

    private fun updatePost(post: Post) {
        val oldList = adapter.posts.toMutableList()
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

    class MainViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                MainViewModel(repository) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
