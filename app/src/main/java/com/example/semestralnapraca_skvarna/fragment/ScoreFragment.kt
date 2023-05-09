package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentScoreBinding
import com.example.semestralnapraca_skvarna.recycleview.ScoreAdapter
import com.example.semestralnapraca_skvarna.view_model.GameRecordViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ScoreFragment : Fragment(R.layout.game_record) {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layout-u (TextView, Button, ImageView)
    private lateinit var viewModel: GameRecordViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI

    private lateinit var adapter : ScoreAdapter //Slúži na spracovanie dát tak, aby s nimi mohol ďalej precovať recyclerView

    private lateinit var firebaseDatabaseReference: DatabaseReference //Referencia na firebase databázu, do ktorej sa zálohujú záznamy (GameRecord) o odohraných hrách
    private lateinit var firebaseDatabase: FirebaseDatabase //Inštancia firebase databázy pre prístup k jej obsahu a jeho získaniu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným

        adapter = ScoreAdapter()
        binding.recyclerView.adapter = adapter //Priradenie adaptéra k recyclerView

        viewModel = ViewModelProvider(this)[GameRecordViewModel::class.java]

        firebaseDatabaseReference = Firebase.database.reference
        firebaseDatabase = FirebaseDatabase.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBackToMenu() //ClickListener pre stlačenie tlačidla

        //viewModel.removeAllData() //Vymazanie všetkých záznamov z Room databázy
        viewModel.getAllData.observe(viewLifecycleOwner) { gameRecord -> //Načítanie všetkých záznamov z Room databázy
            adapter.setGameRecordData(gameRecord) //Posielanie dát adaptéru recyclerView, ktorý si ich pridá do zoznamu na ich následné zobrazenie v recyclerView
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_scoreFragment_to_mainMenuFragment) //Navigovanie sa na fragment MainMenuFragment
        }
    }
}