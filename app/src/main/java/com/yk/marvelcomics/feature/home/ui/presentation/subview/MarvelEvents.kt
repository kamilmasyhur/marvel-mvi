package com.yk.marvelcomics.feature.home.ui.presentation.subview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.databinding.MarvelEventsViewBinding
import com.yk.marvelcomics.feature.detail.presentation.DetailDataView
import com.yk.marvelcomics.feature.home.ui.adapter.EventsAdapter

class MarvelEvents @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MarvelEventsViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = EventsAdapter()

    init {
        binding.rvComics.apply {
            adapter = this@MarvelEvents.adapter
        }
    }

    fun addEventsItem(comicDataView: EventsDataView, listener: EventsListener? = null) {
        val empty = comicDataView.event.isEmpty()
        if (empty) {
            binding.textEmpty.visibility = VISIBLE
            binding.rvComics.visibility = GONE
        } else {
            binding.textEmpty.visibility = GONE
            binding.rvComics.visibility = VISIBLE
            adapter.initItems(comicDataView.event, listener)
        }
        binding.tvSeeAll.setOnClickListener {
            listener?.onSeeAllEventClick()
        }
    }
}

data class EventsDataView(
    val event: List<Event>
) : DetailDataView {
    data class Event(
        val title: String,
        val description: String,
        val thumbnail: String,
        val id: Int?
    )
}

interface EventsListener {
    fun onEventClick(result: EventsDataView.Event)
    fun onSeeAllEventClick() {}
}
