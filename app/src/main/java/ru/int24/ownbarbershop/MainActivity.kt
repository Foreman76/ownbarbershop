package ru.int24.ownbarbershop

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BarberToolBar, HideShowBottomNavView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment:NavHostFragment
    private lateinit var barber_toolBar: TextView
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = findViewById(R.id.bottomNavView)
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

    override fun hideBottomNavView() {
        bottomNavView.visibility = View.GONE
    }

    override fun showBottomNavView() {
        when {
            bottomNavView.visibility == View.GONE -> bottomNavView.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {

    }
}

