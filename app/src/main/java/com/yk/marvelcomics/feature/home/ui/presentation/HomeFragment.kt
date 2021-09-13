package com.yk.marvelcomics.feature.home.ui.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yk.marvelcomics.base.presentation.MVIBaseFragment
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelFragmentHomeBinding
import com.yk.marvelcomics.feature.detail.presentation.DetailActivity
import com.yk.marvelcomics.feature.detail.presentation.DetailPageType
import com.yk.marvelcomics.feature.home.ui.presentation.subview.*
import com.yk.marvelcomics.feature.home.ui.state.HomeContentView
import com.yk.marvelcomics.feature.home.ui.state.MviHomeAction
import com.yk.marvelcomics.feature.home.ui.state.MviHomeIntent
import com.yk.marvelcomics.feature.home.ui.state.MviHomeResult
import com.yk.marvelcomics.feature.home.ui.state.MviHomeViewState
import com.yk.marvelcomics.feature.home.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    MVIBaseFragment<MviHomeIntent, MviHomeAction, MviHomeResult, MviHomeViewState, MarvelFragmentHomeBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val binding by viewBinding(MarvelFragmentHomeBinding::inflate)
    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MviHomeIntent.InitiateHome.let(eventEmitter::onNext) //initiate home, do api call once
        binding.marvelComicsView.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), DetailActivity::class.java))
        }
    }

    override fun intents(): Observable<MviHomeIntent> = Observable.mergeArray(
        eventEmitter.hide()
    )

    override fun render(state: MviHomeViewState) { //start render UI here
        renderLoading(state.loading)
        renderContent(state.content)
        renderError(state.error)
    }

    private fun renderError(error: Throwable?) {
        error?.let {
            /* TODO: add something to show error to user */
        }
    }

    private fun renderContent(content: HomeContentView?) {
        with(binding) {
            content?.let { content ->
                marvelComicsView.addComicsItem(content.comicsData, initComicListener())
                marvelCharactersView.addCharactersItem(content.characters, initCharacterListener())
                marvelEventsView.addEventsItem(content.events, initEventsListener())
            }
            marvelComicsView.isVisible = content?.comicsData != null
            marvelCharactersView.isVisible = content?.characters != null
            marvelEventsView.isVisible = content?.events != null
        }
    }

    fun openDetailPage(id: Int?, detailType: String) {
        startActivity(Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRAS_DETAIL_ID, id)
            putExtra(DetailActivity.EXTRAS_PAGE_TYPE, detailType)
        })
    }

    private fun initEventsListener() = object : EventsListener {
        override fun onEventClick(result: EventsDataView.Event) {
            openDetailPage(result.id, DetailPageType.EVENT_PAGE.name)
        }

    }

    private fun initComicListener() = object : ComicListener {
        override fun onComicClick(result: ComicsDataView.Comic) {
            openDetailPage(result.id, DetailPageType.COMIC_PAGE.name)
        }

    }

    private fun initCharacterListener() = object : CharacterListener {
        override fun onCharacterClick(result: CharactersDataView.Characters) {
            openDetailPage(result.id, DetailPageType.CHARACTER_PAGE.name)
        }

    }

    private fun renderLoading(loading: Boolean) {
        binding.pgHomeLoader.isVisible = loading
    }
}
