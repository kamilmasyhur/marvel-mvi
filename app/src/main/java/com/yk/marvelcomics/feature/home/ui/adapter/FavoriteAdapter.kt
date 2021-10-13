package com.yk.marvelcomics.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.databinding.MarvelItemFavoriteCharacterBinding
import com.yk.marvelcomics.databinding.MarvelItemFavoriteComicBinding
import com.yk.marvelcomics.databinding.MarvelItemFavoriteEventBinding
import com.yk.marvelcomics.databinding.MarvelItemInvalidBinding
import com.yk.marvelcomics.feature.detail.presentation.DetailPageType
import com.yk.marvelcomics.feature.home.data.FavoriteItem

class FavoriteAdapter(
    private val favorites: MutableList<FavoriteItem> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            COMIC_VH -> {
                val binding = MarvelItemFavoriteComicBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                FavComicViewHolder(binding)
            }
            EVENT_VH -> {
                val binding = MarvelItemFavoriteEventBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                FavEventViewHolder(binding)
            }
            CHARACTER_VH -> {
                FavCharacterViewHolder(
                    MarvelItemFavoriteCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> InvalidViewHolder(
                MarvelItemInvalidBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val favoriteItem = favorites[position]
        when (getItemViewType(position)) {
            COMIC_VH -> (holder as FavComicViewHolder).bind(favoriteItem)
            EVENT_VH -> (holder as FavEventViewHolder).bind(favoriteItem)
            CHARACTER_VH -> (holder as FavCharacterViewHolder).bind(favoriteItem)
            INVALID_VH -> (holder as InvalidViewHolder)
        }
    }

    override fun getItemCount(): Int = favorites.size

    override fun getItemViewType(position: Int): Int {
        return when (favorites[position].type) {
            DetailPageType.COMIC_PAGE.name -> COMIC_VH
            DetailPageType.EVENT_PAGE.name -> EVENT_VH
            DetailPageType.CHARACTER_PAGE.name -> CHARACTER_VH
            else -> INVALID_VH
        }
    }

    class FavComicViewHolder(
        private val binding: MarvelItemFavoriteComicBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteItem: FavoriteItem) {
            binding.textItemNameFav.text = favoriteItem.name
        }
    }

    class FavCharacterViewHolder(
        private val binding: MarvelItemFavoriteCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteItem: FavoriteItem) {
            binding.textItemNameFav.text = favoriteItem.name
        }
    }

    class FavEventViewHolder(
        private val binding: MarvelItemFavoriteEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteItem: FavoriteItem) {
            binding.textItemNameFav.text = favoriteItem.name
        }
    }

    class InvalidViewHolder(binding: MarvelItemInvalidBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val COMIC_VH = 1
        const val EVENT_VH = 2
        const val CHARACTER_VH = 3
        const val INVALID_VH = 0
    }

}