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
import com.example.semestralnapraca_skvarna.database.GameRecord
import com.example.semestralnapraca_skvarna.databinding.FragmentGameOverBinding
import com.example.semestralnapraca_skvarna.view_model.GameRecordViewModel
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel

class GameOverFragment : Fragment(R.layout.fragment_game_over) {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameRecordViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[GameRecordViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBackToMenu()
        binding.tvScore.text = sharedViewModel.getScore().toString()
    }

    private fun addGameRecordToDatabase() {
        val profilePicture = sharedViewModel.getIndexOfProfilePicture()
        val username = sharedViewModel.getUsername()
        val game = sharedViewModel.getGame().toString()
        val gameDifficulty = sharedViewModel.getDifficulty().toString()
        val score = sharedViewModel.getScore()

        val gameRecord = GameRecord(0, profilePicture, username, game, gameDifficulty, score)
    }

    private fun addScoreToFirebase() {
        //dorob
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            sharedViewModel.resetScore()
            sharedViewModel.setIsDifficultyChosen(false)
            Navigation.findNavController(binding.root).navigate(R.id.action_gameOverviewFragment_to_mainMenuFragment)
        }
    }
}