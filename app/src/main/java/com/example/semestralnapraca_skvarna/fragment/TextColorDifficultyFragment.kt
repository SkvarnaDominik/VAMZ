package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorDifficultyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel

class TextColorDifficultyFragment : Fragment(R.layout.fragment_simon_color_difficulty) {

    private var _binding: FragmentTextColorDifficultyBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorDifficultyBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickEasy() //ClickListener pre stlačenie tlačidla
        setOnClickHard()
        setOnClickStart()
        setOnClickBackToMenu()
    }

    private fun setOnClickEasy () { //ClickListener pre stlačenie tlačidla
        binding.btnEasy.setOnClickListener() {
            sharedViewModel.setDifficulty("Easy") //Nastavenie obtiažnosti na Easy v zdiaľanom viewModel-y
            sharedViewModel.setIsDifficultyChosen(true) //Nastavenie Flag-u či bola vybraná obtiažnosť na pravdu
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_pressed) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_normal) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
        }
    }

    private fun setOnClickHard () { //ClickListener pre stlačenie tlačidla
        binding.btnHard.setOnClickListener() {
            sharedViewModel.setDifficulty("Hard") //Nastavenie obtiažnosti na Hard v zdiaľanom viewModel-y
            sharedViewModel.setIsDifficultyChosen(true) //Nastavenie Flag-u či bola vybraná obtiažnosť na pravdu
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_normal) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_pressed) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
        }
    }

    private fun setOnClickStart () { //ClickListener pre stlačenie tlačidla
        binding.btnStart.setOnClickListener() {
            if (sharedViewModel.getIsDifficultyChosen()) { //Podmienka pre zistenie či bola vybraná obtiažnosť
                sharedViewModel.setIsFirstRound(true) //Nastavenie flag-u či sa jedná o prvé kolo na pravdu
                Navigation.findNavController(binding.root).navigate(R.id.action_textColorDifficultyFragment_to_countDownFragment) //Navigovanie sa na fragment CountDownFragment
            }
            else
                Toast.makeText(activity, "Choose game difficulty", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorDifficultyFragment_to_mainMenuFragment) //Navigovanie sa na fragment MainManuFragment
        }
    }
}