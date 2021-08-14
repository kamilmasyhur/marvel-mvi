package com.yk.marvelcomics.feature.home.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yk.marvelcomics.R
import com.yk.marvelcomics.base.presentation.MVIBaseFragment
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelFragmentHomeBinding
import com.yk.marvelcomics.extension.exhaustive
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
        binding.tvBlankFragment.text = getString(R.string.marvel_app_name)
        MviHomeIntent.InitiateHome.let(eventEmitter::onNext) //initiate home, do api call once
    }

    override fun intents(): Observable<MviHomeIntent> = Observable.mergeArray(
        eventEmitter.hide()
    )

    override fun render(state: MviHomeViewState) { //start render UI here
        when(state) {
            MviHomeViewState.ConnectionError -> {
                binding.tvBlankFragment.text = "connection error"
            }
            is MviHomeViewState.Content -> {
                binding.tvBlankFragment.text = state.content.toString()
            }
            MviHomeViewState.ShowLoading -> {
                binding.tvBlankFragment.text = "loading"
            }
        }.exhaustive
    }
}
