package com.yk.marvelcomics.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.databinding.MarvelItemComicBinding
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse

class ComicsAdapter(
    private val comics: MutableList<ComicsResponse.Data.Result> = mutableListOf()
) : RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            MarvelItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun getItemCount(): Int = comics.size

    class ComicViewHolder(
        private val binding: MarvelItemComicBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ComicsResponse.Data.Result) {
            binding.txtComicTitle.text = result.title
            val items = result.creators?.items
            val creatorName = if (items.isNullOrEmpty().not()) {
                items?.get(0)?.name
            } else {
                ""
            }
            binding.txtComicCreator.text = creatorName
            val thumbnail = result.thumbnail
        }

    }
}