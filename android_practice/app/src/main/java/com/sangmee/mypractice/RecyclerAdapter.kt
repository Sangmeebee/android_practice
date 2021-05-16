package com.sangmee.mypractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.mypractice.databinding.LayoutPostListItemBinding
import com.sangmee.mypractice.models.Post

class RecyclerAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var posts = mutableListOf<Post>()
    private lateinit var binding: LayoutPostListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding =
            LayoutPostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = RecyclerViewHolder(binding)
        binding.root.setOnClickListener {
            onItemClickListener.onClick(posts[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
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

    interface OnItemClickListener {
        fun onClick(post: Post)
    }

    companion object {
        private const val TAG = "RecyclerAdapter"
    }
}
