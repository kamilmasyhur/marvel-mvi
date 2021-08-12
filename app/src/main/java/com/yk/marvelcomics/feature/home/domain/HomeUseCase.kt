package com.yk.marvelcomics.feature.home.domain

import com.yk.marvelcomics.feature.home.data.HomeRepository
import javax.inject.Inject

interface HomeUseCase {

}

class HomeUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): HomeUseCase {
}
