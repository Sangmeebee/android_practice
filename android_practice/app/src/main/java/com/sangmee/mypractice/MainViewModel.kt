package com.sangmee.mypractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangmee.mypractice.models.Post
import com.sangmee.mypractice.models.repository.PostRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

class MainViewModel(private val repository: PostRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _postData = MutableLiveData<Post>()
    val postData: LiveData<Post>
        get() = _postData

    private val _postsData = MutableLiveData<List<Post>>()
    val postsData: LiveData<List<Post>>
        get() = _postsData

    init {
        getPostsObservable()
            .subscribeOn(Schedulers.io())
            .flatMap {
                getCommentsObservable(it) // return an updated Observable<Post> with comments
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _postData.value = it
            }.addTo(disposables)
    }

    //comment를 불러와서 post에 추가
    private fun getCommentsObservable(post: Post): Observable<Post> {
        return repository.getComments(post.id)
            .map {
                val delay = ((Random.nextInt(5) + 1) * 1000)
                Thread.sleep(delay.toLong())
                post.comments = it
                post
            }
            .subscribeOn(Schedulers.io())
    }


    //post를 불러
    private fun getPostsObservable(): Observable<Post> {
        return repository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                _postsData.value = it
                Observable.fromIterable(it)
                    .subscribeOn(Schedulers.io())
            }
    }

    fun unBindViewModel() {
        disposables.clear()
    }

}
