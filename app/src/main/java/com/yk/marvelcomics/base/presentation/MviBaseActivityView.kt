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

    private lateinit var viewModel: MviBaseViewModel<I, A, R, S>

    abstract fun getViewModel(): MviBaseViewModel<I, A, R, S>


    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preCreate()
        viewModel = getViewModel()
        check(::viewModel.isInitialized) { "ViewModel is not initialized" }
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        populateUI()
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
        _binding = null
        super.onDestroy()
    }

    protected open fun populateUI() {}

    protected open fun postCreate() {}

    protected open fun preCreate() {}
}

