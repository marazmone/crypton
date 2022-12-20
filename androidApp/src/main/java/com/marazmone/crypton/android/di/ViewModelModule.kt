package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.screen.MainViewModel
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailViewModel
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteViewModel
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrencyListViewModel(get(), get()) }
    viewModel { CurrencyDetailViewModel(get(), get()) }
    viewModel { CurrencyFavoriteViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }
}
