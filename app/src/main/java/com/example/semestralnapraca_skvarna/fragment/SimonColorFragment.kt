package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorBinding

class SimonColorFragment : Fragment(R.layout.fragment_simon_color) {

    private var _binding: FragmentSimonColorBinding? = null
    private val binding get() = _binding!!

    private var round:Int = 1
    private var colorOrder = ArrayList<Int>()
    private var score:Int = 0
    private var same:Boolean = true
    private var colorButton:Int = pickRandomColorButton()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentSimonColorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game()
        setOnClickBackToMenu()
    }

    private fun game() {

        colorButton = pickRandomColorButton()
        colorOrder.add(colorButton)

        colorOrder.forEach {
            //signalizeColorButton(it)
        }


        //uzivatel Input

        if (checkUserInput()){
            score.inc()
            binding.textView2.setText(score)
        }
        else {
            same = false
        }
    }

    private fun pickRandomColorButton():Int { //vygenerovanie náhodného čísla z rozsahu 0-3
        return (0..3).random()
    }

    private fun checkUserInput(): Boolean { //overenie stlačenia správneho tlačidla používateľom
        val index:Int = (colorOrder.size - 1)

        if (colorOrder.isEmpty())
            return true
        
        return false
        //return (colorOrder.get(index) == userInput) //porovnanie
    }

    private fun signalizeColorButton(pColorButton:Int) {
        when (colorButton) {
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
        }
    }

    private fun userInputColorButton() {
        
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