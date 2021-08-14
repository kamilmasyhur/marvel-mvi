package com.yk.marvelcomics.base.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.yk.marvelcomics.base.MviAction
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.MviResult
import com.yk.marvelcomics.base.MviView
import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class MVIBaseFragment<
    I : MviIntent,
    A : MviAction,
    R : MviResult,
    S : MviViewState,
    VB : ViewBinding
    > : MviView<I, S>, Fragment() {

    private val compositeDisposable = CompositeDisposable()
    protected lateinit var eventEmitter: PublishSubject<I>

    abstract val viewModel: MviBaseViewModel<I, A, R, S>

    abstract val binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventEmitter = PublishSubject.create()
        viewModel.state()
            .subscribe(::render) { e ->
                Log.d(this.javaClass.simpleName, "Error" + e.message)
            }
            .let(compositeDisposable::add)
        intents()
            .let(viewModel::processIntents)
            .let(compositeDisposable::add)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        eventEmitter.onComplete()
        super.onDestroy()
    }
}

