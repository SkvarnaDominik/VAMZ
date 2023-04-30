package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorDifficultyBinding
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorBinding

class TextColorFragment : Fragment(R.layout.fragment_text_color) {

    private var _binding: FragmentTextColorBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBackToMenu()
    }

    private fun setOnClickBackToMenu() {
        binding.backToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_mainMenuFragment)
        }
    }
}