package com.example.bestsurfspots

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.bestsurfspots.fragments.AddSpotFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(FirstFragment())

        // Importer navigation bar
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.add_spot -> {
                    loadFragment(AddSpotFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home_page -> {
                    loadFragment(FirstFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_content_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }


}