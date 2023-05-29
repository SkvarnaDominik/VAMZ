package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentMainMenuBinding
import com.example.semestralnapraca_skvarna.databinding.FragmentMenuScoreBinding
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorEasyBinding
import kotlinx.coroutines.launch

class MenuScoreFragment :  Fragment(R.layout.fragment_menu_score) {

    private var _binding: FragmentMenuScoreBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuScoreBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickLocalScore() //ClickListener pre stlačenie tlačidla
        setOnClickOnlineScore()
        setOnClickBackToMenu()
    }


    private fun setOnClickLocalScore() { //ClickListener pre stlačenie tlačidla
        binding.btnLocalScore.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_menuScoreFragment_to_scoreFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }

    private fun setOnClickOnlineScore() { //ClickListener pre stlačenie tlačidla
        binding.btnOnlineScore.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_menuScoreFragment_to_scoreFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnMainMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorEasyFragment_to_mainMenuFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }
}