package com.sangmee.mypractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.mypractice.databinding.LayoutPostListItemBinding
import com.sangmee.mypractice.models.Post

class RecyclerAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Post, RecyclerAdapter.RecyclerViewHolder>(diffUtil) {

    private lateinit var binding: LayoutPostListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding =
            LayoutPostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(binding).apply {
            binding.root.setOnClickListener {
                onItemClickListener.onClick(getItem(this.absoluteAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
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

        val diffUtil = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return false
            }
        }
    }
}
