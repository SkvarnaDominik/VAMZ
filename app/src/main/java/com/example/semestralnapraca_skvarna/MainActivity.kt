package com.example.semestralnapraca_skvarna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.semestralnapraca_skvarna.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //Nastavenie hodnôt premenným

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) //obrazovka nikdy nezhasne

        setContentView(binding.root) //Nastavenie contentView na binding.root (activity_main.xml)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() { //zabezpecenie, ze po kliknuti na tlacidlo spat, sa nic nestane

    }
}