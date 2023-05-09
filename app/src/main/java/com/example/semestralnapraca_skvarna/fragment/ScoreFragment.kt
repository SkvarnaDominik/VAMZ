package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.database.GameRecord
import com.example.semestralnapraca_skvarna.databinding.FragmentScoreBinding
import com.example.semestralnapraca_skvarna.recycleview.ScoreAdapter
import com.example.semestralnapraca_skvarna.view_model.GameRecordViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ScoreFragment : Fragment(R.layout.game_record) {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private lateinit var viewModel: GameRecordViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI

    private lateinit var adapter : ScoreAdapter

    private lateinit var firebaseDatabaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)

        adapter = ScoreAdapter()
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[GameRecordViewModel::class.java]
        //viewModel.removeAllData()
        viewModel.getAllData.observe(viewLifecycleOwner) { gameRecord ->
            adapter.setGameRecordData(gameRecord)
        }

        firebaseDatabaseReference = Firebase.database.reference
        firebaseDatabase = FirebaseDatabase.getInstance()


        //readFirebaseData()
        return binding.root
    }

    /*private fun readFirebaseData() {
        var gameRecordList = ArrayList<GameRecord>()

        firebaseDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    var gameRecord = dataSnapshot.getValue(GameRecord::class.java)
                    gameRecordList.add(gameRecord!!)
                }
                Log.e("data", "onDataChange$snapshot")
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickBackToMenu()
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_scoreFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}