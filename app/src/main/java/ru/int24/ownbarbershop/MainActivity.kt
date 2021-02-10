package ru.int24.ownbarbershop

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.int24.ownbarbershop.`interface`.BarberToolBar
import ru.int24.ownbarbershop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BarberToolBar {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment:NavHostFragment
    private lateinit var barber_toolBar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottomNavView)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        setBarberToolBar()



    }

    fun setBarberToolBar(){

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.elevation = 0F
        barber_toolBar = findViewById(R.id.barber_actionBar_title)
    }

    override fun setToolBarTitle(bTitle: String) {
        barber_toolBar.text = bTitle
    }

}

