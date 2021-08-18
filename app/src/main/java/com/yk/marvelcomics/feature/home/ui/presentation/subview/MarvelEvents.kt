package com.yk.marvelcomics.feature.home.ui.presentation.subview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.databinding.MarvelEventsViewBinding
import com.yk.marvelcomics.feature.home.ui.adapter.CharactersAdapter
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

    fun addEventsItem(comicDataView: EventsDataView) =
        adapter.initItems(comicDataView.event)
}

data class EventsDataView(
    val event: List<Event>
) {
    data class Event(
        val title: String,
        val description: String,
        val thumbnail: String
    )
}
