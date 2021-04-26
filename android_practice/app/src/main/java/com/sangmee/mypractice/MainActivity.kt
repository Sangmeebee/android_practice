package com.sangmee.mypractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.mypractice.databinding.ActivityMainBinding
import com.sangmee.rxjavapractice.models.Post
import com.sangmee.rxjavapractice.requests.ServiceGenerator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { RecyclerAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        getPostsObservable()
            .subscribeOn(Schedulers.io())
            .flatMap {
                getCommentsObservable(it) // return an updated Observable<Post> with comments
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Post> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onNext(t: Post) {
                    updatePost(t)
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "onError ${e?.message}")
                }
            })
    }

    private fun updatePost(post: Post) {
        adapter.updatePost(post)
    }

    private fun getCommentsObservable(post: Post) =
        ServiceGenerator.getRequestApi().getComments(post.id)
            .map {
                val delay = ((Random.nextInt(5) + 1) * 1000)
                Thread.sleep(delay.toLong())
                Log.d(TAG, "apply: sleeping thread: ${Thread.currentThread().name} for ${delay}ms")
                post.comments = it
                post
            }
            .subscribeOn(Schedulers.io())

    private fun getPostsObservable() =
        ServiceGenerator.getRequestApi().getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                adapter.setPosts(it)
                Observable.fromIterable(it)
                    .subscribeOn(Schedulers.io())
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

    companion object {
        private const val TAG = "MainActivity"
    }
}
