package com.studies.cinedroid.di

import com.studies.cinedroid.ui.home.list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MovieViewModelModule {

    val module = module {
        viewModel {
            MovieListViewModel(get())
        }
    }
}