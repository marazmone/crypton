package com.marazmone.crypton.android.presentation.screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marazmone.crypton.android.presentation.navigation.Arguments
import com.marazmone.crypton.android.presentation.navigation.NavScreen
import com.marazmone.crypton.android.presentation.screen.detail.CurrencyDetailScreen
import com.marazmone.crypton.android.presentation.screen.list.CurrencyListScreen
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Main


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                Surface(
                    color = Main.Background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Navigation(navController = navController)
    }

    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = NavScreen.CurrencyList.route) {
            composable(NavScreen.CurrencyList.route) {
                CurrencyListScreen(navController = navController)
            }
            composable(NavScreen.CurrencyDetail.route) { backStackEntry ->
                val currencyId = backStackEntry.arguments?.getString(Arguments.CURRENCY_DETAIL_ID)
                requireNotNull(currencyId) {
                    "currency id parameter wasn't found. Please make sure it's set!"
                }
                CurrencyDetailScreen(id = currencyId, navController = navController)
            }
        }
    }
}
