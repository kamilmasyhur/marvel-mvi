package com.yk.marvelcomics.feature.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelItemComicBinding
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView

class ComicsAdapter : ListAdapter<ComicsDataView.Comic, ComicViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            MarvelItemComicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun initItems(comics: List<ComicsDataView.Comic>) {
        submitList(comics)
        notifyDataSetChanged()
    }

    companion object {
        private val diffUtilItemCallback = object : ItemCallback<ComicsDataView.Comic>() {
            override fun areItemsTheSame(
                oldItem: ComicsDataView.Comic,
                newItem: ComicsDataView.Comic
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ComicsDataView.Comic,
                newItem: ComicsDataView.Comic
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class ComicViewHolder(
    private val binding: MarvelItemComicBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ComicsDataView.Comic) = with(binding) {
        txtComicTitle.text = result.title
        txtComicCreator.text = result.creator
        imgItemComic.load(result.thumbnail)
    }
}
