package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentScoreBinding
import com.example.semestralnapraca_skvarna.recycleview.ScoreAdapter
import com.example.semestralnapraca_skvarna.view_model.GameRecordViewModel

class ScoreFragment : Fragment(R.layout.game_record_row) {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie
    private lateinit var viewModel: GameRecordViewModel

    private lateinit var adapter : ScoreAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)

        adapter = ScoreAdapter()
        binding.recyclerView.adapter = adapter
        //recyclerView.layoutManager = LinearLayoutManager(requireContext())



        viewModel = ViewModelProvider(this)[GameRecordViewModel::class.java]
        //viewModel.removeAllData()
        viewModel.getAllData.observe(viewLifecycleOwner, Observer { gameRecord ->
            adapter.setGameRecordData(gameRecord)
        })



        return binding.root
    }

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