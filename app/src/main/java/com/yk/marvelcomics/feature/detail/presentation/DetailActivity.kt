package com.yk.marvelcomics.feature.detail.presentation

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yk.marvelcomics.R
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.presentation.MviBaseActivityView
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelActivityDetailBinding
import com.yk.marvelcomics.feature.detail.viewmodel.DetailViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class DetailActivity :
    MviBaseActivityView<DetailIntent, DetailAction, DetailResult, DetailViewState, MarvelActivityDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val detailIntent: PublishSubject<MviIntent> = PublishSubject.create()

    override fun getViewModel(): MviBaseViewModel<DetailIntent, DetailAction, DetailResult, DetailViewState> {
        return ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override val binding by viewBinding(MarvelActivityDetailBinding::inflate)

    override fun intents(): Observable<DetailIntent> = Observable.mergeArray(
        detailIntent.ofType(DetailIntent.LoadPage::class.java),
        eventEmitter.hide()
    )

    override fun render(state: DetailViewState) {
        state.dataViews?.let {
            Toast.makeText(this, it.firstOrNull().toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun preCreate() {
        super.preCreate()
        setSupportActionBar(binding.toolbar)
    }

    override fun postCreate() {
        super.postCreate()
        val pageType = intent.getStringExtra(EXTRAS_PAGE_TYPE).orEmpty()
        val detailId = intent.getIntExtra(EXTRAS_DETAIL_ID, -1)
        eventEmitter.onNext(DetailIntent.LoadPage(pageType, detailId))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.marvel_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRAS_PAGE_TYPE = "EXTRAS_PAGE_TYPE"
        const val EXTRAS_DETAIL_ID = "EXTRAS_DETAIL_ID"
    }

}