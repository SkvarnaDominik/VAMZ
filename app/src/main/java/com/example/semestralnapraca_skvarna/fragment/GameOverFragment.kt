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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GameOverFragment : Fragment(R.layout.fragment_game_over) {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private lateinit var viewModel: GameRecordViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private lateinit var firebaseDatabaseReference: DatabaseReference //Referencia na firebase databázu, do ktorej sa zálohujú záznamy (GameRecord) o odohraných hrách

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameOverBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        viewModel = ViewModelProvider(this)[GameRecordViewModel::class.java]

        firebaseDatabaseReference = Firebase.database.reference

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBackToMenu() //ClickListener pre stlačenie tlačidla
        addGameRecordToRoomDatabase() //Metóda pre zápis záznamu o odohranej hre (GameRecord) do Room databázy
        addGameRecordToFirebase() //Metóda pre zápis záznamu o odohranej hre (GameRecord) do Firebase databázy
        binding.tvScore.text = sharedViewModel.getScore().toString() //Výpis skóre do textView tvScore
    }

    private fun addGameRecordToRoomDatabase() { //Metóda pre zápis záznamu o odohranej hre (GameRecord) do Room databázy
        val profilePicture = sharedViewModel.getIndexOfProfilePicture() //Nastavovanie hodnôt premenných zo zdieľaného viewModel-u
        val username = sharedViewModel.getUsername()
        val game = sharedViewModel.getGame()
        val gameDifficulty = sharedViewModel.getDifficulty()
        val score = sharedViewModel.getScore()

        val gameRecord = GameRecord(0, profilePicture, username, game, gameDifficulty, score) //Vytvorenie premennej záznamu hry (GameRecord) s danými vlastnosťami

        viewModel.addGameRecord(gameRecord) //Pridanie záznamu (GameRecord) do Room databázy
    }

    private fun addGameRecordToFirebase() { //Metóda pre zápis záznamu o odohranej hre (GameRecord) do Firebase databázy
        val profilePicture = sharedViewModel.getIndexOfProfilePicture() //Nastavovanie hodnôt premenných zo zdieľaného viewModel-u
        val username = sharedViewModel.getUsername()
        val game = sharedViewModel.getGame()
        val gameDifficulty = sharedViewModel.getDifficulty()
        val score = sharedViewModel.getScore()


        val gameRecord = GameRecord(0, profilePicture, username, game, gameDifficulty, score) //Vytvorenie premennej záznamu hry (GameRecord) s danými vlastnosťami

        firebaseDatabaseReference.child("gameRecord").child(username).setValue(gameRecord) //Pridanie záznamu (GameRecord) do Firebase databázy do "podpriečinka" gameRecord
                                                                                                    // do priečinka s názvom meno používateľa
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            sharedViewModel.resetScore() //Resetovanie skóre po odohraní a zapísaní hry do databáz
            sharedViewModel.setIsDifficultyChosen(false) //Resetovanie výberu odbtiažnosti
            sharedViewModel.setIsFirstRound(false) //Nastavenie Boolean-u či sa jedná o prvé kolo na nepravdu
            Navigation.findNavController(binding.root).navigate(R.id.action_gameOverviewFragment_to_mainMenuFragment) //Navigovanie sa na fragment MainMenuFragment
        }
    }
}