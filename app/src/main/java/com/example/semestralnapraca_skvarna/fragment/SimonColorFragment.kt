package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorBinding
import com.example.semestralnapraca_skvarna.view_model.SimonColorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonColorFragment : Fragment(R.layout.fragment_simon_color) {

    private var _binding: FragmentSimonColorBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SimonColorViewModel

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSimonColorBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SimonColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBtnSimonYellow()
        setOnClickBtnSimonBlue()
        setOnClickBtnSimonRed()
        setOnClickBtnSimonGreen()
        setOnClickBackToMenu()

        viewModel.pickRandomColorButton()
        coroutineScope.launch {
            playSequence()
        }

    }

    private fun letUserChooseColor() {
        if (viewModel.getColorButtonsPressed() != viewModel.getRound())
            letUserChooseColor()
    }

     private fun checkUserSequence() { //overenie stlačenia správneho tlačidla používateľom
         for (index in 0..viewModel.getGameSequence().size - 1) {
             if (viewModel.getGameSequence()[index] != viewModel.getUserSequence()[index])
                 viewModel.setIsSame(false)
         }
         binding.tvScore.text = "asdfsdgfhf"
     }

    private suspend fun playSequence() {
        binding.btnSimonYellow.isClickable = false
        binding.btnSimonBlue.isClickable = false
        binding.btnSimonRed.isClickable = false
        binding.btnSimonGreen.isClickable = false
        for (index in 0 .. viewModel.getGameSequence().size - 1) {
            when (viewModel.getGameSequence()[index]) {
                0 -> {
                    delay(250)
                    binding.btnSimonYellow.setBackgroundResource(R.drawable.btn_yellow_pressed)
                    delay(250)
                    binding.btnSimonYellow.setBackgroundResource(R.drawable.btn_yellow)
                }
                1 -> {
                    delay(250)
                    binding.btnSimonBlue.setBackgroundResource(R.drawable.btn_blue_pressed)
                    delay(250)
                    binding.btnSimonBlue.setBackgroundResource(R.drawable.btn_blue)
                }
                2 -> {
                    delay(250)
                    binding.btnSimonRed.setBackgroundResource(R.drawable.btn_red_pressed)
                    delay(250)
                    binding.btnSimonRed.setBackgroundResource(R.drawable.btn_red)
                }
                3 -> {
                    delay(250)
                    binding.btnSimonGreen.setBackgroundResource(R.drawable.btn_green_pressed)
                    delay(250)
                    binding.btnSimonGreen.setBackgroundResource(R.drawable.btn_green)
                }
            }
        }
        binding.btnSimonYellow.isClickable = true
        binding.btnSimonBlue.isClickable = true
        binding.btnSimonRed.isClickable = true
        binding.btnSimonGreen.isClickable = true
    }

    private fun setOnClickBtnSimonYellow() {
        binding.btnSimonYellow.setOnClickListener() {
            viewModel.addToUserSequence(0)
            viewModel.addColorButtonsPressed()
            if (viewModel.getColorButtonsPressed() == viewModel.getRound())
                checkUserSequence()
            if (viewModel.getIsSame()) {
                viewModel.addRound()
                viewModel.pickRandomColorButton()
                coroutineScope.launch {
                    playSequence()
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
        }
    }

    private fun setOnClickBtnSimonBlue() {
        binding.btnSimonBlue.setOnClickListener() {
            viewModel.addToUserSequence(1)
            viewModel.addColorButtonsPressed()
            if (viewModel.getColorButtonsPressed() == viewModel.getRound())
                checkUserSequence()
            if (viewModel.getIsSame()) {
                viewModel.addRound()
                viewModel.pickRandomColorButton()
                coroutineScope.launch {
                    playSequence()
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
        }
    }

    private fun setOnClickBtnSimonRed() {
        binding.btnSimonRed.setOnClickListener() {
            viewModel.addToUserSequence(2)
            viewModel.addColorButtonsPressed()
            if (viewModel.getColorButtonsPressed() == viewModel.getRound())
                checkUserSequence()
            if (viewModel.getIsSame()) {
                viewModel.addRound()
                viewModel.pickRandomColorButton()
                coroutineScope.launch {
                    playSequence()
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
        }
    }

    private fun setOnClickBtnSimonGreen() {
        binding.btnSimonGreen.setOnClickListener() {
            viewModel.addToUserSequence(3)
            viewModel.addColorButtonsPressed()
            if (viewModel.getColorButtonsPressed() == viewModel.getRound())
                checkUserSequence()
            if (viewModel.getIsSame()) {
                viewModel.addRound()
                viewModel.pickRandomColorButton()
                coroutineScope.launch {
                    playSequence()
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
        }
    }

    private fun setOnClickBackToMenu() {
        binding.backToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_mainMenuFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}