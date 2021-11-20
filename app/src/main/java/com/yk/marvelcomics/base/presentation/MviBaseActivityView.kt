package com.yk.marvelcomics.base.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yk.marvelcomics.base.MviAction
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.MviResult
import com.yk.marvelcomics.base.MviView
import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class MviBaseActivityView<
    I : MviIntent,
    A : MviAction,
    R : MviResult,
    S : MviViewState,
    VB : ViewBinding
    > : MviView<I, S>, AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    protected lateinit var eventEmitter: PublishSubject<I>

    abstract val viewModel: MviBaseViewModel<I, A, R, S>

    abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preCreate()
        setContentView(binding.root)
        eventEmitter = PublishSubject.create()
        viewModel.state()
            .subscribe(::render) { e ->
                Log.d(this.javaClass.simpleName, "Error" + e.message + "\n")
                e.printStackTrace()
            }.let(compositeDisposable::add)
        intents()
            .let(viewModel::processIntents)
            .let(compositeDisposable::add)
        postCreate()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        eventEmitter.onComplete()
        super.onDestroy()
    }

    protected open fun postCreate() {}

    protected open fun preCreate() {}
}

