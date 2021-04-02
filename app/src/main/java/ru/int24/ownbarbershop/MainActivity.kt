package ru.int24.ownbarbershop

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.int24.ownbarbershop.UiInterface.ArrowBack
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BarberToolBar, HideShowBottomNavView,ArrowBack {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment:NavHostFragment
    private lateinit var barber_toolBar: TextView
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var imageArrowBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = findViewById(R.id.bottomNavView)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        setBarberToolBar()
        imageArrowBack = findViewById(R.id.id_arrow_back)
    }

    fun setBarberToolBar(){

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.elevation = 0F
        barber_toolBar = findViewById(R.id.barber_actionBar_title)
    }

    override fun setToolBarTitle(screenTitle: String) {
        barber_toolBar.text = screenTitle
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

    override fun hideShowArrowBack(hideArrow:Boolean) {
        when(hideArrow){
            true -> imageArrowBack.visibility = View.GONE
            false -> imageArrowBack.visibility = View.VISIBLE
        }
    }

    override fun comeBackInstanceImageView(): ImageView {
        return imageArrowBack
    }

    override fun handlerOnClick(function: () -> Unit) {
        imageArrowBack.setOnClickListener { function() }
    }

}

