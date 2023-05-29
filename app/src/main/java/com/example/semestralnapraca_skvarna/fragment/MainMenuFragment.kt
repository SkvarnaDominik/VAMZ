package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentMainMenuBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným
        sharedViewModel.getGameLiveData().observe(viewLifecycleOwner) {}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickSimonColor() //ClickListener pre stlačenie tlačidla
        setOnClickTextColor()
        setOnClickScore()
        setOnClickSettings()
    }

    private fun setOnClickSimonColor () { //ClickListener pre stlačenie tlačidla
        binding.btnSimonColor.setOnClickListener() {
            sharedViewModel.setGame("SimonColor") //Nastavenie herného módu na SimonColor
            sharedViewModel.getGameLiveData().observe(
                viewLifecycleOwner,
                Observer {  //Sledovanie zmeny premennej Game, ktorá vyjadruje herný mód, ak sa jej hodnota zmenila
                                   //vykoná sa telo Observer-a
                    Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_simonColorDifficultyFragment) //Navigovanie sa na Fragment SimonColorDifficultyFragment
                })
        }
    }

    private fun setOnClickTextColor () { //ClickListener pre stlačenie tlačidla
        binding.btnTextColor.setOnClickListener() {
                sharedViewModel.setGame("TextColor") //Nastavenie herného módu na Text/Color
                sharedViewModel.getGameLiveData().observe(
                    viewLifecycleOwner,
                    Observer {  //Sledovanie zmeny premennej Game, ktorá vyjadruje herný mód, ak sa jej hodnota zmenila
                                       //vykoná sa telo Observer-a
                        Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_textColorDifficultyFragment) //Navigovanie sa na Fragment TextColorDifficultyFragment
                    })
            }
    }

    private fun setOnClickScore () { //ClickListener pre stlačenie tlačidla
        binding.btnScore.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_menuScoreFragment) //Navigovanie sa na fragment ScoreMenuFragment
        }
    }

    private fun setOnClickSettings () { //ClickListener pre stlačenie tlačidla
        binding.btnSettings.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenu_to_settings) //Navigovanie sa na fragment SettingsFragment
        }
    }
}

