package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.screen.detail.CurrencyDetailViewModel
import com.marazmone.crypton.android.presentation.screen.list.CurrencyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrencyListViewModel(get(), get()) }
    viewModel { CurrencyDetailViewModel() }
}