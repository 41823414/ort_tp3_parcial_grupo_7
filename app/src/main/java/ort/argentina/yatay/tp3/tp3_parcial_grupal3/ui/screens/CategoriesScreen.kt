package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BalanceSection
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.CategoryHeader
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.IconCategory
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.CaribbeanGreenLight
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.VoidGreenDark

/**
 * Categories Screen - Pantalla de categorías
 * Pantalla principal con categorías de transacciones
 */
@Composable
fun CategoriesScreen(
    onNavigateBack: () -> Unit = {},
    onShowNotifications: () -> Unit = {}
) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) VoidGreenDark else CaribbeanGreenLight

    // Lista de categorías con sus datos
    val categories = listOf(
        CategoryItem("Food", R.drawable.dish, colorResource(R.color.ocean_blue)),
        CategoryItem("Transport", R.drawable.bus, colorResource(R.color.light_blue)),
        CategoryItem("Medicine", R.drawable.pill, colorResource(R.color.light_blue)),
        CategoryItem("Groceries", R.drawable.food_bag, colorResource(R.color.light_blue)),
        CategoryItem("Rent", R.drawable.hand_key, colorResource(R.color.light_blue)),
        CategoryItem("Gifts", R.drawable.gift, colorResource(R.color.light_blue)),
        CategoryItem("Savings", R.drawable.coins, colorResource(R.color.light_blue)),
        CategoryItem("Entertainment", R.drawable.ticket, colorResource(R.color.light_blue)),
        CategoryItem("More", R.drawable.plus, colorResource(R.color.light_blue))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Contenido superior (Header + Balance)
        Column(
            modifier = Modifier
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Category Header
            CategoryHeader(
                title = stringResource(R.string.categories_title),
                onBackClick = onNavigateBack,
                onNotificationClick = onShowNotifications
            )

            Spacer(
                Modifier.height(24.dp)
            )
            // 2. Balance Section
            BalanceSection()
        }

        Spacer(
            Modifier.height(24.dp)
        )
        // Caja blanca que se extiende al final
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(if (isDarkTheme) colorResource(R.color.cyprus) else Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                // Grid de categorías 3x3
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(categories) { category ->
                        IconCategory(
                            icon = painterResource(category.iconRes),
                            label = category.name,
                            backgroundColor = category.color
                        )
                    }
                }
            }
        }
    }
}

/**
 * Data class para representar una categoría
 */
data class CategoryItem(
    val name: String,
    val iconRes: Int,
    val color: Color
)

