package com.adrorodri.annotationsexamples.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.adrorodri.annotationsexamples.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
