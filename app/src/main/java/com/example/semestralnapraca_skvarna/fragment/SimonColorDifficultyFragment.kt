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
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorDifficultyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class SimonColorDifficultyFragment : Fragment(R.layout.fragment_simon_color_difficulty) {

    private var _binding: FragmentSimonColorDifficultyBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private lateinit var mediaPlayer : MediaPlayer //MediaPlayer pre prehratie zvukov

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimonColorDifficultyBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setDifficulty("None") //Nastavenie obtiaznosti
        binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue) //Zmena pozadia tlačidla
        binding.btnHard.setBackgroundResource(R.drawable.btn_red) //Zmena pozadia tlačidla

        setOnClickEasy() //ClickListener pre stlačenie tlačidla
        setOnClickHard()
        setOnClickStart()
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

    private fun setOnClickEasy () { //ClickListener pre stlačenie tlačidla
        binding.btnEasy.setOnClickListener() {
            playSoundButton() //prehratie zvuku
            sharedViewModel.setDifficulty("Easy") //Nastavenie obtiažnosti na Easy v zdiaľanom viewModel-y
            sharedViewModel.setIsDifficultyChosen(true) //Nastavenie Flag-u či bola vybraná obtiažnosť na pravdu
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_pressed) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_normal) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
        }
    }

    private fun setOnClickHard () { //ClickListener pre stlačenie tlačidla
        binding.btnHard.setOnClickListener() {
            playSoundButton() //prehratie zvuku
            sharedViewModel.setDifficulty("Hard") //Nastavenie obtiažnosti na Hard v zdiaľanom viewModel-y
            sharedViewModel.setIsDifficultyChosen(true) //Nastavenie Flag-u či bola vybraná obtiažnosť na pravdu
            binding.btnEasy.setBackgroundResource(R.drawable.btn_dark_blue_normal) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
            binding.btnHard.setBackgroundResource(R.drawable.btn_red_pressed) //Zmena pozadia tlačidla poďľa vybranej obtiažnosti
        }
    }

    private fun setOnClickStart () { //ClickListener pre stlačenie tlačidla
        binding.btnStart.setOnClickListener() {
            if (sharedViewModel.getIsDifficultyChosen()) { //Podmienka pre zistenie či bola vybraná obtiažnosť
                playSoundButton() //prehratie zvuku
                sharedViewModel.setIsFirstRound(true) //Nastavenie flag-u či sa jedná o prvé kolo na pravdu
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorDifficultyFragment_to_countDownFragment) //Navigovanie sa na fragment CountDownFragment
            }
           else {
                playSoundToast() //prehranie zvuku
                Toast.makeText(activity, "Choose game difficulty", Toast.LENGTH_SHORT)
                    .show() //Výpis krátkej správy na obrazovku
            }
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            playSoundButton() //prehratie zvuku
            sharedViewModel.resetScore() //Resetovanie skóre po odohraní a zapísaní hry do databáz
            sharedViewModel.setIsDifficultyChosen(false) //Resetovanie výberu odbtiažnosti
            sharedViewModel.setIsFirstRound(false) //Nastavenie Flag-u či sa jedná o prvé kolo na nepravdu
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorDifficultyFragment_to_mainMenuFragment) //Navigovanie sa na fragment MainManuFragment
        }
    }
}