package com.example.suitmedia_mobile

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.suitmedia_mobile.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondScreenBinding
    var name: String = "Anonymous"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        name = intent.getStringExtra("name").toString()
        if (name.isEmpty()) {
            name = "Anonymous"
        }
        val bundle = Bundle()
        bundle.putString("name", name)
        val navController = findNavController(R.id.nav_host_fragment_content_second_screen)
        navController.setGraph(R.navigation.nav_graph, bundle)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_second_screen)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}