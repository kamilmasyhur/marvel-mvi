package com.yk.marvelcomics.feature.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelItemHeroBinding
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView

class CharactersAdapter :
    ListAdapter<CharactersDataView.Characters, HeroViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            MarvelItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun initItems(comics: List<CharactersDataView.Characters>) {
        submitList(comics)
        notifyDataSetChanged()
    }

    companion object {
        private val diffUtilItemCallback =
            object : DiffUtil.ItemCallback<CharactersDataView.Characters>() {
                override fun areItemsTheSame(
                    oldItem: CharactersDataView.Characters,
                    newItem: CharactersDataView.Characters
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: CharactersDataView.Characters,
                    newItem: CharactersDataView.Characters
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}

class HeroViewHolder(
    private val binding: MarvelItemHeroBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result: CharactersDataView.Characters) {
        binding.imageItemHero.load(result.thumbnail)
    }
}
