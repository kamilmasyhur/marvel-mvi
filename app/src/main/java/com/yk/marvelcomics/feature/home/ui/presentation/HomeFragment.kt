package com.yk.marvelcomics.feature.home.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yk.marvelcomics.R
import com.yk.marvelcomics.common.viewBinding
import com.yk.marvelcomics.databinding.MarvelFragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by viewBinding(MarvelFragmentHomeBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBlankFragment.text = getString(R.string.marvel_app_name)
    }
}
