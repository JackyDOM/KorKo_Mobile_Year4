package com.example.korkoapp.ui.element.Activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.korkoapp.R
import com.example.korkoapp.ui.element.Fragment.CafeFragment
import com.example.korkoapp.ui.element.Fragment.FoodFragment
import com.example.korkoapp.ui.element.Fragment.SeaFragment
import com.example.korkoapp.ui.element.Fragment.TempleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityButtonNavBar : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var bottomNaviView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_button_nav_bar)

        frameLayout = findViewById(R.id.frameLayout)
        bottomNaviView = findViewById(R.id.bottomNaviView)

        bottomNaviView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navFood -> loadFragment(FoodFragment(), false)
                R.id.navSea -> loadFragment(SeaFragment(), false)
                R.id.navTemple -> loadFragment(TempleFragment(), false)
                else -> loadFragment(CafeFragment(), false)
            }
            true
        }

        loadFragment(FoodFragment(), true)


    }

    private fun loadFragment(fragment: Fragment, isAppInitialized: Boolean) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment)
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}