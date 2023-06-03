package com.example.semestralnapraca_skvarna.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentMenuScoreBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel

class MenuScoreFragment :  Fragment(R.layout.fragment_menu_score) {

    private var _binding: FragmentMenuScoreBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private lateinit var mediaPlayer : MediaPlayer //MediaPlayer pre prehratie zvukov

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuScoreBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickLocalScore() //ClickListener pre stlačenie tlačidla
        setOnClickOnlineScore()
        setOnClickBackToMenu()
    }

    private fun playSoundButton() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.button) //Mediaplayer pre prehratie zvukov
        if (mediaPlayer.isPlaying) { //ak sa zvuk prehrava
            mediaPlayer.pause() //zastavenie zvuku
            mediaPlayer.seekTo(0) //pretocenie na zaciatok
        }
        mediaPlayer.start() //zapnutie prehravania
    }

    private fun playSoundToast() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.toast) //Mediaplayer pre prehratie zvukov
        if (mediaPlayer.isPlaying) { //ak sa zvuk prehrava
            mediaPlayer.pause() //zastavenie zvuku
            mediaPlayer.seekTo(0) //pretocenie na zaciatok
        }
        mediaPlayer.start() //zapnutie prehravania
    }

    private fun setOnClickLocalScore() { //ClickListener pre stlačenie tlačidla
        binding.btnLocalScore.setOnClickListener() {
            playSoundButton() //prehratie zvuku
            sharedViewModel.setScoreType("Local")
            Navigation.findNavController(binding.root).navigate(R.id.action_menuScoreFragment_to_scoreFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }


    //Táto funkcionalita nie je naimplementovaná. Môže slúžiť pre zlepšenie dojmu a funkcionality aplikácie.
    private fun setOnClickOnlineScore() { //ClickListener pre stlačenie tlačidla
        binding.btnOnlineScore.setOnClickListener() {
            playSoundToast() //prehratie zvuku
            Toast.makeText(activity, "To be added...", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
            sharedViewModel.setScoreType("Online")
            //Navigation.findNavController(binding.root).navigate(R.id.action_menuScoreFragment_to_scoreFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnMainMenu.setOnClickListener() {
            playSoundButton() //prehratie zvuku
            Navigation.findNavController(binding.root).navigate(R.id.action_menuScoreFragment_to_mainMenuFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }
}