package com.example.semestralnapraca_skvarna.fragment

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentMainMenuBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {


    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private lateinit var mediaPlayer : MediaPlayer //MediaPlayer pre prehratie zvukov

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným
        sharedViewModel.getGameLiveData().observe(viewLifecycleOwner) {}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickSimonColor() //ClickListener pre stlačenie tlačidla
        setOnClickTextColor()
        setOnClickScore()
        setOnClickSettings()
    }

    private fun playSoundButton() {
        if(!this::mediaPlayer.isInitialized)
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.button) //Mediaplayer pre prehratie zvukov
        if (mediaPlayer.isPlaying) { //ak sa zvuk prehrava
            mediaPlayer.pause() //zastavenie zvuku
            mediaPlayer.seekTo(0) //pretocenie na zaciatok
        }
        mediaPlayer.start() //zapnutie prehravania
    }

    private fun setOnClickSimonColor () { //ClickListener pre stlačenie tlačidla
        binding.btnSimonColor.setOnClickListener() {
            sharedViewModel.setGame("SimonColor") //Nastavenie herného módu na SimonColor
            playSoundButton()
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_simonColorDifficultyFragment) //Navigovanie sa na Fragment SimonColorDifficultyFragment
        }
    }

    private fun setOnClickTextColor () { //ClickListener pre stlačenie tlačidla
        binding.btnTextColor.setOnClickListener() {
            sharedViewModel.setGame("TextColor") //Nastavenie herného módu na Text/Color
            playSoundButton()
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_textColorDifficultyFragment) //Navigovanie sa na Fragment TextColorDifficultyFragment
            }
    }

    private fun setOnClickScore () { //ClickListener pre stlačenie tlačidla
        binding.btnScore.setOnClickListener() {
            playSoundButton()
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_menuScoreFragment) //Navigovanie sa na fragment ScoreMenuFragment
        }
    }

    private fun setOnClickSettings () { //ClickListener pre stlačenie tlačidla
        binding.btnSettings.setOnClickListener() {
            playSoundButton()
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenu_to_settings) //Navigovanie sa na fragment SettingsFragment
        }
    }
}

