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
import com.example.semestralnapraca_skvarna.databinding.FragmentCountDownBinding
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorDifficultyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel

class TextColorDifficultyFragment : Fragment(R.layout.fragment_simon_color_difficulty) {

    private var _binding: FragmentTextColorDifficultyBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorDifficultyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setIsDifficultyChosen(false)
        setOnClickEasy()
        setOnClickHard()
        setOnClickStart()
    }

    private fun setOnClickEasy () {
        binding.btnEasy.setOnClickListener() {
            sharedViewModel.setDifficulty("Easy")
            sharedViewModel.setIsDifficultyChosen(true)
            binding.btnEasy.setBackgroundResource(R.drawable.btn_blue_pressed)
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_normal)
        }
    }

    private fun setOnClickHard () {
        binding.btnHard.setOnClickListener() {
            sharedViewModel.setDifficulty("Hard")
            sharedViewModel.setIsDifficultyChosen(true)
            binding.btnEasy.setBackgroundResource(R.drawable.btn_blue_normal)
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_pressed)
        }
    }

    private fun setOnClickStart () {
        binding.btnStart.setOnClickListener() {
            if (sharedViewModel.getIsDifficultyChosen()) {
                Navigation.findNavController(binding.root).navigate(R.id.action_textColorDifficultyFragment_to_countDownFragment)
            }
            else
                Toast.makeText(activity, "Nevybrali ste herný mód", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}