package ru.int24.ownbarbershop

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.UiInterface.InterfaceArrowBack
import ru.int24.ownbarbershop.UiInterface.InterfaceUpdateDefConfig
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.databinding.ActivityMainBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMMainActivity
import ru.int24.ownbarbershop.models.domen.DomSettings
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BarberToolBar, HideShowBottomNavView, InterfaceArrowBack, InterfaceUpdateDefConfig {


    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment:NavHostFragment
    private lateinit var barber_toolBar: TextView
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var imageArrowBack: ImageView
    private lateinit var vmMainActivity: VMMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.appComponent.inject(this@MainActivity)

        bottomNavView = findViewById(R.id.bottomNavView)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        setBarberToolBar()
        imageArrowBack = findViewById(R.id.id_arrow_back)
        vmMainActivity = ViewModelProvider(this@MainActivity, modelFactory).get(VMMainActivity::class.java)
        vmMainActivity.getSettingsFromVM().observe(this, { initSettings(it) } )

        readSettingsFromDB()
    }

    private fun initSettings(domSettings: DomSettings?) {
        domSettings?.let { DefConfig.settings = domSettings }
    }

    private fun readSettingsFromDB() {
        vmMainActivity.getSettingsFromDB()
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


    override fun handlerOnClick(function: () -> Unit) {
        imageArrowBack.setOnClickListener { function() }
    }

    override fun updateDefconfig() {
        readSettingsFromDB()
    }

}

