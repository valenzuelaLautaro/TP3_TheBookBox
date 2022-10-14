package com.tp3.tp3_thebookbox.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var navHostFragment: NavHostFragment
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navBar)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }
}