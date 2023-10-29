package com.example.jet_ecommerce.ui.navigation_comp

import com.example.jet_ecommerce.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Categories: BottomNavItem("Categories",R.drawable.ic_catageroy,"categories")
    object WishList: BottomNavItem("WishList",R.drawable.ic_wishlist,"wishlist")
    object Profile: BottomNavItem("Profile",R.drawable.ic_profile,"profile")
}