package com.yk.marvelcomics.feature.detail.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.yk.marvelcomics.R
import com.yk.marvelcomics.base.presentation.MviBaseActivityView
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelActivityDetailBinding
import io.reactivex.rxjava3.core.Observable

class DetailActivity : MviBaseActivityView<DetailIntent, DetailAction, DetailResult, DetailViewState, MarvelActivityDetailBinding>() {

    override fun getViewModel(): MviBaseViewModel<DetailIntent, DetailAction, DetailResult, DetailViewState> {
        TODO("Not yet implemented")
    }

    override val binding by viewBinding(MarvelActivityDetailBinding::inflate)

    override fun intents(): Observable<DetailIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: DetailViewState) {
        TODO("Not yet implemented")
    }

    override fun preCreate() {
        super.preCreate()
        setSupportActionBar(binding.toolbar)
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

}