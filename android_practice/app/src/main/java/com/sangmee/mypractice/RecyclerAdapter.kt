package com.sangmee.mypractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.mypractice.databinding.LayoutPostListItemBinding
import com.sangmee.rxjavapractice.models.Post

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private var posts = ArrayList<Post>()
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

    fun setPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun updatePost(post: Post) {
        with(posts.indexOf(post)) {
            posts.add(this, post)
            notifyItemChanged(this)
        }
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
                    post.comments.size.toString()
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
