package com.example.lend.navigation.naivgation_drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerModel(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)