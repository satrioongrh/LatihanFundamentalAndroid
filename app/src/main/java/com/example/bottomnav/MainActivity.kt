package com.example.bottomnav

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnav.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

        val navController = findNavController(R.id.fl_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.exploreFragment, R.id.subsctiptionFragment, R.id.libraryFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }




//        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
//
//        val navController = findNavController(R.id.fl_fragment)
//
//        val appBarConfiguration = AppBarConfiguration.Builder(
//            R.id.homeFragment, R.id.exploreFragment, R.id.subsctiptionFragment, R.id.libraryFragment
//        ).build()
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)



//        val fr = supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fl_fragment, HomeFragment())
//            .commit()


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_fragment, ExploreFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }
            R.id.menu2 -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return false
        }
    }
}


//yt
//        val btnBotNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        btnBotNav.setOnNavigationItemSelectedListener(onBottomListener)
//
//    }
//        private val onBottomListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
//                var selectedFr: Fragment = HomeFragment()
//
//            when(i.itemId){
//
//                R.id.home ->
//                    selectedFr = HomeFragment()
//                R.id.explore ->
//                    selectedFr = ExploreFragment()
//                R.id.subscription ->
//                    selectedFr = SubsctiptionFragment()
//                R.id.folder ->
//                    selectedFr = LibraryFragment()
//            }
//
//            val fr = supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fl_fragment, selectedFr)
//                .commit()
//
//            true }

