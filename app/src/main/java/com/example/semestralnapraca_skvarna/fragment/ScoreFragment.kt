package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentScoreBinding

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickStart()
    }

    private fun setOnClickStart () {
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_scoreFragment_to_mainMenuFragment)
        }
    }
}