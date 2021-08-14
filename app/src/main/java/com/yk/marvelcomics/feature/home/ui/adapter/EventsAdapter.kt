package com.yk.marvelcomics.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.databinding.MarvelItemEventBinding
import com.yk.marvelcomics.feature.home.data.response.EventsResponse

class EventsAdapter(
    private val events: MutableList<EventsResponse.Data.Result>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            MarvelItemEventBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    class EventViewHolder(private val binding: MarvelItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: EventsResponse.Data.Result) {
            val thumbnail = result.thumbnail
            val title = result.title
            val description = result.description
            binding.txtItemEventTitle.text = title
            binding.txtItemEventDescription.text = description
        }

    }
}
