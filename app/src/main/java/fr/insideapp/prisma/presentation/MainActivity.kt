package fr.insideapp.prisma.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.insideapp.prisma.R
import fr.insideapp.prisma.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_activity_main
        ) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.navView.setupWithNavController(navController)

        // Setup the ActionBar with navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_news, R.id.navigation_favorites)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}