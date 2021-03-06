package com.yk.marvelcomics.feature.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelItemEventBinding
import com.yk.marvelcomics.feature.home.ui.presentation.subview.EventsDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.EventsListener

class EventsAdapter :
    ListAdapter<EventsDataView.Event, EventViewHolder>(diffUtilItemCallback) {

    private var listener: EventsListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            MarvelItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun initItems(comics: List<EventsDataView.Event>, listener: EventsListener?) {
        submitList(comics)
        this.listener = listener
        notifyDataSetChanged()
    }

    companion object {
        private val diffUtilItemCallback =
            object : DiffUtil.ItemCallback<EventsDataView.Event>() {
                override fun areItemsTheSame(
                    oldItem: EventsDataView.Event,
                    newItem: EventsDataView.Event
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: EventsDataView.Event,
                    newItem: EventsDataView.Event
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}

class EventViewHolder(
    private val binding: MarvelItemEventBinding,
    private val listener: EventsListener?
): RecyclerView.ViewHolder(binding.root) {
    fun bind(result: EventsDataView.Event) {
        binding.txtItemEventTitle.text = result.title
        binding.txtItemEventDescription.text = result.description
        binding.imageItemEvent.load(result.thumbnail)
        binding.root.setOnClickListener {
            listener?.onEventClick(result)
        }
    }

}
