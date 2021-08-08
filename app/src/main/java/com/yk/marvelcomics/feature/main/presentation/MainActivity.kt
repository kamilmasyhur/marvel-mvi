package com.yk.marvelcomics.feature.main.presentation

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.presentation.MviBaseActivityView
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import com.yk.marvelcomics.databinding.ActivityMainBinding
import com.yk.marvelcomics.extension.exhaustive
import com.yk.marvelcomics.feature.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MviBaseActivityView<
    MainIntent,
    MainAction,
    MainResult,
    MainViewState,
    ActivityMainBinding>() {

    private val intent: PublishSubject<MviIntent> = PublishSubject.create()
    @Inject lateinit var viewModelFactory: ViewModelFactory

    //registering any event possibilities
    override fun intents(): Observable<MainIntent> = Observable.mergeArray(
        intent.ofType(MainIntent.SampleIntent::class.java),
        eventEmitter.hide()
    )

    override fun postCreate() {
        MainIntent.SampleIntent.let(eventEmitter::onNext)
    }

    override fun render(state: MainViewState) {
        when(state) {
            is MainViewState.InitialState -> renderInitialState(state)
        }.exhaustive
    }
    override fun getViewModel(): MviBaseViewModel<MainIntent, MainAction, MainResult, MainViewState> {
        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override val bindingInflater = object: (LayoutInflater) -> ActivityMainBinding {
        override fun invoke(layoutInflater: LayoutInflater): ActivityMainBinding {
            return ActivityMainBinding.inflate(layoutInflater)
        }

    }

    private fun renderInitialState(state: MainViewState.InitialState) {
        binding.tvMain.text = state.data.message
    }
}
