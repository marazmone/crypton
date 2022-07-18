package com.marazmone.crypton.android.presentation.navigation.bottom_menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.marazmone.crypton.android.R

sealed class NavigationItem(
    @DrawableRes
    val icon: Int,
    @StringRes
    val title: Int,
    val route: String,
) {

    object HOME : NavigationItem(
        icon = R.drawable.ic_menu_home,
        title = R.string.menu_home,
        route = "home"
    )

    object FAVORITE : NavigationItem(
        icon = R.drawable.ic_menu_favorite,
        title = R.string.menu_favorite,
        route = "favorite"
    )
}