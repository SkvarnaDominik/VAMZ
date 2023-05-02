package com.example.semestralnapraca_skvarna.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.TextColorViewModel
import kotlinx.coroutines.launch

class TextColorFragment : Fragment(R.layout.fragment_text_color) {

    private var _binding: FragmentTextColorBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie

    private lateinit var viewModel: TextColorViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[TextColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYellow.setTextColor(Color.BLACK)
        binding.btnBlue.setTextColor(Color.BLACK)
        binding.btnRed.setTextColor(Color.BLACK)
        binding.btnGreen.setTextColor(Color.BLACK)
        setOnClickBtnYellow()
        setOnClickBtnBlue()
        setOnClickBtnRed()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        gameSetup()
    }

    private fun gameSetup() {
        viewModel.shuffleModes()
        binding.tvMode.text = viewModel.getMode()

        viewModel.shuffleTextColors()
        binding.tvColor.text = viewModel.getColor()

        viewModel.shuffleTextColors()
        viewModel.shuffleColorButtons()
        setupColorButtons()
    }
   private fun game(pColorButtonNumber:Int) {
            isSameAsChosen(pColorButtonNumber)
            if (viewModel.getIsSame()) {
                sharedViewModel.addScore()
                setupColorButtons()
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorFragment_to_gameOverviewFragment)
            }
    }

    private fun setupColorButtons() {
        binding.btnYellow.setBackgroundResource(viewModel.getColorButtons()[0])
        binding.btnYellow.text = viewModel.getTextColors()[0]
        binding.btnBlue.setBackgroundResource(viewModel.getColorButtons()[1])
        binding.btnBlue.text = viewModel.getTextColors()[1]
        binding.btnRed.setBackgroundResource(viewModel.getColorButtons()[2])
        binding.btnRed.text = viewModel.getTextColors()[2]
        binding.btnGreen.setBackgroundResource(viewModel.getColorButtons()[3])
        binding.btnGreen.text = viewModel.getTextColors()[3]
    }

    private fun isSameAsChosen(pColorButtonNumber: Int) {
        when (pColorButtonNumber) {
            0 -> {
                if (viewModel.getMode() == "Text") {
                    if (binding.btnYellow.text == viewModel.getColor()){
                        sharedViewModel.addScore()
                    }
                    else {
                        viewModel.setIsSame(false)
                    }
                }
            }

            1 -> {
                if (viewModel.getMode() == "Text") {
                    if (binding.btnBlue.text == viewModel.getColor()){
                        sharedViewModel.addScore()
                    }
                    else {
                        viewModel.setIsSame(false)
                    }
                }
            }

            2 -> {
                if (viewModel.getMode() == "Text") {
                    if (binding.btnRed.text == viewModel.getColor()){
                        sharedViewModel.addScore()
                    }
                    else {
                        viewModel.setIsSame(false)
                    }
                }
            }

            3 -> {
                if (viewModel.getMode() == "Text") {
                    if (binding.btnGreen.text == viewModel.getColor()){
                        sharedViewModel.addScore()
                    }
                    else {
                        viewModel.setIsSame(false)
                    }
                }
            }
        }
    }

   private fun setOnClickBtnYellow() {
        binding.btnYellow.setOnClickListener() {
           game(0, )
        }
    }

    private fun setOnClickBtnBlue() {
        binding.btnBlue.setOnClickListener() {
           game(1)
        }
    }

    private fun setOnClickBtnRed() {
        binding.btnRed.setOnClickListener() {
            game(2)
        }
    }

    private fun setOnClickBtnGreen() {
        binding.btnGreen.setOnClickListener() {
            game(3)
        }
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}