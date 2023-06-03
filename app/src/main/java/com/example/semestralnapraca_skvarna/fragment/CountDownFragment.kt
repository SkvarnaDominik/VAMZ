package com.example.semestralnapraca_skvarna.fragment

import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentCountDownBinding
import com.example.semestralnapraca_skvarna.view_model.CountDownViewModel
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class CountDownFragment : Fragment(R.layout.fragment_count_down) {

    private var _binding: FragmentCountDownBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private lateinit var viewModel: CountDownViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI

    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private lateinit var mediaPlayer : MediaPlayer //MediaPlayer pre prehratie zvukov

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountDownBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startTimer(3) //Spustenie Timer-u
        displaySeconds() //Zobrazenie sekúnd
        isFinished() //Kontrola skončenia Timer-u

    }

    private fun playSoundClock() {
        if(!this::mediaPlayer.isInitialized)
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.clock) //Mediaplayer pre prehratie zvukov
        if (mediaPlayer.isPlaying) { //ak sa zvuk prehrava
            mediaPlayer.pause() //zastavenie zvuku
            mediaPlayer.seekTo(0) //pretocenie na zaciatok
        }
        mediaPlayer.start() //zapnutie prehravania
    }

    private fun displaySeconds() {
        viewModel.getSeconds().observe(viewLifecycleOwner, Observer { //Observer sleduje liveData. Pokiaľ sa dáta zmenia vykoná svoje telo
            playSoundClock() //prehranie zvuku
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) { // Zisťovanie orientácie zariadenia: Portrait/Landscape
                when (it) {
                    2 -> binding.root.setBackgroundResource(R.drawable.green3) //Prepínanie pozadia Fragmentu podľa odrátaných sekúnd
                    1 -> binding.root.setBackgroundResource(R.drawable.blue2)
                    0 -> binding.root.setBackgroundResource(R.drawable.red1)
                }
            }
            else {
                when (it) {
                    2 -> binding.root.setBackgroundResource(R.drawable.green3_land) //Prepínanie pozadia Fragmentu podľa odrátaných sekúnd
                    1 -> binding.root.setBackgroundResource(R.drawable.blue2_land)
                    0 -> binding.root.setBackgroundResource(R.drawable.red1_land)
                }
            }

            binding.tvSeconds.text = (it.inc()  ).toString() //Výpis sekúnd do textView tvSeconds
        })
    }

    private fun isFinished() {
        viewModel.getIsFinished().observe(viewLifecycleOwner, Observer {//Observer sleduje liveData. Pokiaľ sa dáta zmenia vykoná svoje telo
            if (it && sharedViewModel.getGame() == "SimonColor") { //Podmienka pre zistenie herného režimu: SimonColor - Text/Color
                if (sharedViewModel.getDifficulty() == "Easy") //Podmienka pre zistenie obtiažnosti: Easy/Hard
                    Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_simonColorEasyFragment) //Navigovanie sa na fragment SimonColorEasyFragment
                else
                    Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_simonColorHardFragment) //Navigovanie sa na fragment SimonColorHardFragment
            }
                else
                Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_textColorFragment) //Navigovanie sa na fragment TextColorFragment
        })
    }
}