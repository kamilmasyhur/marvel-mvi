package com.yk.marvelcomics.feature.home.data

import com.yk.marvelcomics.repository.MarvelApi
import javax.inject.Inject

interface HomeRepository {
}

class HomeRepositoryImpl @Inject constructor(
    private val api: MarvelApi
): HomeRepository {

}
