package com.moonturns.marketlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nav = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, nav)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
}