package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.ViewModel
import com.surya.androidjetpackpro.data.repositories.AppRepository

class TvshowViewModel(
    private val repository: AppRepository
) : ViewModel() {
    val tvShowList = repository.getRemoteTvShow()
}
