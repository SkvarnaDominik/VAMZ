package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorDifficultyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class SimonColorDifficultyFragment : Fragment(R.layout.fragment_simon_color_difficulty) {

    private var _binding: FragmentSimonColorDifficultyBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimonColorDifficultyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setIsDifficultyChosen(false)
        setOnClickEasy()
        setOnClickHard()
        setOnClickStart()
        setOnClickBackToMenu()
    }

    private fun setOnClickEasy () {
        binding.btnEasy.setOnClickListener() {
            sharedViewModel.setDifficulty("Easy")
            sharedViewModel.setIsDifficultyChosen(true)
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_pressed)
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_normal)
        }
    }

    private fun setOnClickHard () {
        binding.btnHard.setOnClickListener() {
            sharedViewModel.setDifficulty("Hard")
            sharedViewModel.setIsDifficultyChosen(true)
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_normal)
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_pressed)
        }
    }

    private fun setOnClickStart () {
        binding.btnStart.setOnClickListener() {
            if (sharedViewModel.getIsDifficultyChosen()) {
                sharedViewModel.setIsFirstRound(true)
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorDifficultyFragment_to_countDownFragment)
            }
            else
                Toast.makeText(activity, "Choose game difficulty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            sharedViewModel.resetScore()
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorDifficultyFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}