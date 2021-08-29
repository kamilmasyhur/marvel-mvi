package com.yk.marvelcomics.feature.detail.data

import com.yk.marvelcomics.repository.MarvelApi
import javax.inject.Inject

interface DetailRepository {

}

class DetailRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : DetailRepository {

}