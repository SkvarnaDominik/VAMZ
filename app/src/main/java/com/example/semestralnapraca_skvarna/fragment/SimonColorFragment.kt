package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorBinding
import com.example.semestralnapraca_skvarna.view_model.SimonColorViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonColorFragment : Fragment(R.layout.fragment_simon_color) {

    private var _binding: FragmentSimonColorBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SimonColorViewModel

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

        game()
        setOnClickBackToMenu()
        //displaySequence()
    }

    private fun game() {
        viewModel.pickRandomColorButton()
        GlobalScope.launch {
            playSequence()
        }
    }

   /* private fun checkUserInput(): Boolean { //overenie stlačenia správneho tlačidla používateľom
        val index:Int = (colorOrder.size - 1)

        if (colorOrder.isEmpty())
            return true
        
        return false
        //return (colorOrder.get(index) == userInput) //porovnanie
    }*/

    private fun signalizeColorButton(pColorButton:Int) {
      /*  when (colorButton) {
            0 -> { //binding.btnSimonYellow.setBackgroundColor(R.drawable.btn_yellow_pressed)
                   binding.btnSimonYellow.setBackgroundResource(R.drawable.btn_yellow) }
            1 -> { //binding.btnSimonBlue.setBackgroundColor(R.drawable.btn_blue_pressed)
                    //Thread.sleep(1_000)
                   binding.btnSimonBlue.setBackgroundColor(R.drawable.btn_blue) }
            2 -> { //binding.btnSimonRed.setBackgroundColor(R.drawable.btn_red_pressed)
                    //Thread.sleep(1_000)
                   binding.btnSimonRed.setBackgroundColor(R.drawable.btn_red) }
            3 -> { //binding.btnSimonGreen.setBackgroundColor(R.drawable.btn_green_pressed)
                    //Thread.sleep(1_000)
                   binding.btnSimonGreen.setBackgroundColor(R.drawable.btn_green) }
        }*/
    }

    private fun userInputColorButton() {
        
    }

    private fun setOnClickBackToMenu() {
        binding.backToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_mainMenuFragment)
        }
    }

    private suspend fun playSequence() {
        binding.btnSimonYellow.isClickable = false
        binding.btnSimonBlue.isClickable = false
        binding.btnSimonRed.isClickable = false
        binding.btnSimonGreen.isClickable = false
        for (index in 0 .. viewModel.getSequence().size - 1) {
            when (viewModel.getSequence()[index]) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}