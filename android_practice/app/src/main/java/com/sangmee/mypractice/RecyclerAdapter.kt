package com.sangmee.mypractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.mypractice.databinding.LayoutPostListItemBinding
import com.sangmee.rxjavapractice.models.Post

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private var posts = mutableListOf<Post>()
    private lateinit var binding: LayoutPostListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding =
            LayoutPostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun submitList(newPosts: List<Post>) {
        val oldList = posts
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(RecyclerDiffUtil(oldList, newPosts))
        posts.clear()
        posts.addAll(newPosts)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updatePost(post: Post) {
        val oldList = posts
        val newList = posts as ArrayList
        with(newList.indexOf(post)) {
            newList[this] = post
        }
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(RecyclerDiffUtil(oldList, newList))
        this.posts = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class RecyclerDiffUtil(private val oldList: List<Post>, private val newList: List<Post>) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size


        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

    class RecyclerViewHolder(private val binding: LayoutPostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.tvTitle.text = post.title
            binding.tvNumComments.apply {
                text = if (post.comments == null) {
                    showProgressBar(true)
                    ""
                } else {
                    showProgressBar(false)
                    post.comments?.size.toString()
                }
            }
        }

        private fun showProgressBar(showProgressBar: Boolean) {
            binding.pb.isVisible = showProgressBar
        }
    }

    companion object {
        private const val TAG = "RecyclerAdapter"
    }
}
