package com.yk.marvelcomics.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.databinding.MarvelItemHeroBinding
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse

class CharactersAdapter(
    private val characters: MutableList<CharactersResponse.Data.Result>
) : RecyclerView.Adapter<CharactersAdapter.HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            MarvelItemHeroBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class HeroViewHolder(private val binding: MarvelItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: CharactersResponse.Data.Result) {
            val thumbnail = result.thumbnail
        }

    }
}