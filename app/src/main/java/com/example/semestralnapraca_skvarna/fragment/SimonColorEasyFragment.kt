package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorEasyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.SimonColorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonColorEasyFragment : Fragment(R.layout.fragment_simon_color_easy) {

    private var _binding: FragmentSimonColorEasyBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SimonColorViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSimonColorEasyBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SimonColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBtnRed()
        setOnClickBtnBlue()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        viewModel.pickRandomColorButton("easy")
        coroutineScope.launch {
            playSequence()
        }
    }

    private fun game(pColorButtonNumber:Int) {
        viewModel.addToUserSequence(pColorButtonNumber)
        viewModel.addColorButtonsPressed()
        if (viewModel.getColorButtonsPressed() == viewModel.getRound()) {
            checkUserSequence()
            if (viewModel.getIsSame()) {
                viewModel.clearUserSequence()
                sharedViewModel.addScore()
                binding.tvScore.text = sharedViewModel.getScore().toString()
                viewModel.setColorButtonsPressed(0)
                viewModel.addRound()
                viewModel.pickRandomColorButton("easy")
                coroutineScope.launch {
                    playSequence()
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
        }
    }

    private fun checkUserSequence() { //overenie stlačenia správneho tlačidla používateľom
        for (index in 0 until viewModel.getGameSequence().size) {
            if (viewModel.getGameSequence()[index] != viewModel.getUserSequence()[index])
                viewModel.setIsSame(false)
        }
    }

    private suspend fun playSequence() {
        binding.btnRed.isClickable = false
        binding.btnBlue.isClickable = false
        binding.btnGreen.isClickable = false
        for (index in 0 until viewModel.getGameSequence().size) {
            when (viewModel.getGameSequence()[index]) {
                0 -> {
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red_pressed)
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red)
                }
                1 -> {
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue_pressed)
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue)
                }
                2 -> {
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green_pressed)
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green)
                }
            }
        }
        binding.btnRed.isClickable = true
        binding.btnBlue.isClickable = true
        binding.btnGreen.isClickable = true
    }

    private fun setOnClickBtnBlue() {
        binding.btnRed.setOnClickListener() {
            game(0)
        }
    }

    private fun setOnClickBtnRed() {
        binding.btnBlue.setOnClickListener() {
            game(1)
        }
    }

    private fun setOnClickBtnGreen() {
        binding.btnGreen.setOnClickListener() {
            game(2)
        }
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            sharedViewModel.resetScore()
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}