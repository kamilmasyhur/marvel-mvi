package com.yk.marvelcomics.feature.main.presentation

import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import com.yk.marvelcomics.R
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.presentation.MviBaseActivityView
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelActivityMainBinding
import com.yk.marvelcomics.extension.exhaustive
import com.yk.marvelcomics.feature.home.ui.presentation.HomeFragment
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
    MarvelActivityMainBinding>(),
    NavigationBarView.OnItemSelectedListener {

    private val intent: PublishSubject<MviIntent> = PublishSubject.create()
    private val pages = mapOf(
        Pair(HOME, HomeFragment()),
        Pair(FAVORITE, BlankFragment()), //TODO: need to be updated
        Pair(PROFILE, BlankFragment()), //TODO: need to be updated
    )
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val binding by viewBinding(MarvelActivityMainBinding::inflate)

    override val viewModel: MviBaseViewModel<MainIntent, MainAction, MainResult, MainViewState>
        get() = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            //registering any event possibilities
    override fun intents(): Observable<MainIntent> = Observable.mergeArray(
        intent.ofType(MainIntent.SampleIntent::class.java),
        eventEmitter.hide()
    )

    override fun postCreate() {
        setupViewPager()
        MainIntent.SampleIntent.let(eventEmitter::onNext)
    }

    override fun render(state: MainViewState) {
        when (state) {
            is MainViewState.InitialState -> renderInitialState(state)
        }.exhaustive
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btmNavHome -> binding.viewPager.currentItem = HOME
            R.id.btmNavFavorite -> binding.viewPager.currentItem = FAVORITE
            R.id.btmNavProfile -> binding.viewPager.currentItem = PROFILE
        }
        return true
    }

    private fun setupViewPager() = with(binding) {
        btmNavMain.setOnItemSelectedListener(this@MainActivity)

        val adapter = BottomNavAdapter(this@MainActivity)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var prevMenuItem: MenuItem? = null
            private val navMain = binding.btmNavMain

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                prevMenuItem?.let {
                    it.isChecked = false
                } ?: run {
                    navMain.menu.getItem(HOME).isChecked = true
                }
                navMain.menu.getItem(position).isChecked = true

                prevMenuItem = navMain.menu.getItem(position)

            }
        })

        adapter.addFragments(pages)

        viewPager.offscreenPageLimit = 1
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
    }

    private fun renderInitialState(state: MainViewState.InitialState) {

    }

    companion object {
        const val HOME = 0
        const val FAVORITE = 1
        const val PROFILE = 2
    }

}
