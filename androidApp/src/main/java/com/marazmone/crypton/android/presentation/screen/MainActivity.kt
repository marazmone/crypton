package com.marazmone.crypton.android.presentation.screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marazmone.crypton.android.presentation.navigation.Arguments
import com.marazmone.crypton.android.presentation.navigation.NavScreen
import com.marazmone.crypton.android.presentation.navigation.bottom_menu.NavigationItem
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailScreen
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteScreen
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListScreen
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors
import com.marazmone.crypton.android.presentation.ui.Colors.Main.Background
import com.marazmone.crypton.android.presentation.ui.NoRippleTheme
import com.marazmone.crypton.android.presentation.util.PaddingValuesBottom

private const val BOTTOM_BAR_ANIMATION_HEIGHT = 128

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                Surface(
                    color = Background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        val needBottom = remember { mutableStateOf(true) }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = needBottom.value,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { BOTTOM_BAR_ANIMATION_HEIGHT }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { BOTTOM_BAR_ANIMATION_HEIGHT }),
                ) {
                    BottomNavigationView(navController)
                }
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                Box(modifier = Modifier.padding(innerPadding)) {
                    Navigation(navController = navController) {
                        needBottom.value = it
                    }
                }
            }
        }
    }

    @Composable
    fun Navigation(navController: NavHostController, needBottom: (Boolean) -> Unit) {
        NavHost(navController = navController, startDestination = NavigationItem.HOME.route) {
            composable(NavigationItem.HOME.route) {
                CurrencyListScreen(navController)
                needBottom.invoke(true)
            }
            composable(NavigationItem.FAVORITE.route) {
                CurrencyFavoriteScreen(navController)
                needBottom.invoke(true)
            }
            composable(NavScreen.CurrencyDetail.route) { backStackEntry ->
                val currencyId = backStackEntry.arguments?.getString(Arguments.CURRENCY_DETAIL_ID)
                requireNotNull(currencyId) {
                    "currency id parameter wasn't found. Please make sure it's set!"
                }
                CurrencyDetailScreen(id = currencyId, navController = navController)
                needBottom.invoke(false)
            }
        }
    }

    @Composable
    fun BottomNavigationView(navController: NavHostController) {
        val items = listOf(
            NavigationItem.HOME,
            NavigationItem.FAVORITE
        )
        Column {
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                NavigationBar(
                    containerColor = Colors.Main.BackgroundSecond,
                    contentColor = Color.White,
                    tonalElevation = 12.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 15.dp
                            )
                        ),
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.title),
                                    modifier = Modifier.size(32.dp)
                                )
                            },
                            onClick = {
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            alwaysShowLabel = false,
                            colors = NavigationBarItemDefaults.colors(
                                unselectedIconColor = Color.White,
                                indicatorColor = Colors.Main.BottomNavigationBarSelectedItem
                            )
                        )
                    }
                }
            }
            Spacer(
                Modifier
                    .background(Colors.Main.BackgroundSecond)
                    .padding(PaddingValuesBottom())
                    .fillMaxWidth()
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun BottomNavigationBarPreview() {
        val navController = rememberNavController()
        MaterialTheme {
            BottomNavigationView(navController)
        }
    }
}
