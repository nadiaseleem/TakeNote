package com.example.TakeNote.ui.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.TakeNote.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpToolbar()


    }

    private fun setUpToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
        navController = findNavController(R.id.fragmentContainerView)

        // Set up the NavigationUI with the NavigationController
        NavigationUI.setupActionBarWithNavController(this, navController)
        // appBarConfiguration = AppBarConfiguration(navController.graph)

        // setupActionBarWithNavController(navController, appBarConfiguration)
    }


}