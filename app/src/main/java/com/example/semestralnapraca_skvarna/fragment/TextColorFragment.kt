package com.example.semestralnapraca_skvarna.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.TextColorViewModel

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

        binding.tvScore.text = sharedViewModel.getScore().toString()

        setOnClickBtnYellow()
        setOnClickBtnBlue()
        setOnClickBtnRed()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        timerSetup()
        displaySeconds()
        isFinished()
        gameSetup()
    }

    private fun timerSetup(){
        if (sharedViewModel.getDifficulty() == "Easy") {
          viewModel.startTimer(5)
          return
        }
        viewModel.startTimer(3)
    }

    private fun gameSetup() {
        viewModel.shuffleModes()
        viewModel.setMode(viewModel.getModes()[0])
        binding.tvMode.text = viewModel.getMode()

        viewModel.shuffleTextColors()
        viewModel.setColor(viewModel.getTextColors()[0])
        binding.tvColor.text = viewModel.getTextColor()

        viewModel.shuffleTextColors()
        viewModel.shuffleColorButtons()

        setupColorButtons()

        viewModel.setWinningColorButton(determinateWinningButton())
    }
   private fun game(pColorButtonNumber:Int) {
            isSameAsChosen(determinateWinningButton(), pColorButtonNumber)
            if (viewModel.getIsSame()) {
               viewModel.restartTimer()
                sharedViewModel.addScore()
                binding.tvScore.text = sharedViewModel.getScore().toString()
                gameSetup()
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_gameOverviewFragment)
            }
    }

    private fun determinateWinningButton(): Int {
        if (viewModel.getMode() == "Text") {
            for (index in 0 until viewModel.getTextColors().size) {
                if (viewModel.getTextColors()[index] == viewModel.getTextColor())
                    return index
            }
        }
       else {
           when (viewModel.getTextColor()) {
               "Yellow" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165323") //Toto je (R.drawable.btn_yellow).toString()
                           return index
                   }
               }

               "Blue" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165306")
                           return index
                   }
               }

               "Red" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165320")
                           return index
                   }
               }

               "Green" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165313")
                           return index
                   }
               }
           }
        }
        return -1
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

    private fun isSameAsChosen(pWinningButtonNumber: Int,pColorButtonNumber: Int) {
        if (pWinningButtonNumber != pColorButtonNumber)
            viewModel.setIsSame(false)
    }

    private fun displaySeconds() {
        viewModel.getSeconds().observe(viewLifecycleOwner, Observer {
            binding.tvSeconds.text = (it.inc()  ).toString()
        })
    }

    private fun isFinished() {
        viewModel.getIsFinished().observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_gameOverviewFragment)
        })
    }

   private fun setOnClickBtnYellow() {
        binding.btnYellow.setOnClickListener() {
           game(0 )
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
            sharedViewModel.resetScore()
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}