package com.example.semestralnapraca_skvarna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.semestralnapraca_skvarna.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*private val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    var navController = navHostFragment.navController
    val fragmentMainMenu = MainMenu()
    val fragmentSimonColor = SimonColor()
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) //obrazovka nikdy nezhasne


        /*supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainActivityLayout, fragmentMainMenu)
            commit()
        }

        binding.btnFragmentMainMenu.setOnClickListener {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.mainActivityLayout, fragmentMainMenu)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnFragmentSimonColor.setOnClickListener {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.mainActivityLayout, fragmentSimonColor)
                addToBackStack(null)
                commit()
            }
        }*/
    }
}