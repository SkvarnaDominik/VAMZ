package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentCountDownBinding
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorDifficultyBinding

class TextColorDifficultyFragment : Fragment(R.layout.fragment_simon_color_difficulty) {

    private var _binding: FragmentTextColorDifficultyBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorDifficultyBinding.inflate(inflater, container, false)

        //binding.seconds.text = sharedViewModel.getGame().value
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickStart()
    }

    private fun setOnClickStart () {
        binding.btnStart.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorDifficultyFragment_to_countDownFragment)
        }
    }
}