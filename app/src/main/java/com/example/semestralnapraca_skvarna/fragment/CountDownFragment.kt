package com.example.semestralnapraca_skvarna.fragment

import android.content.res.Configuration
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
    private val binding get() = _binding!! //binding pre lahsie pristupovanie
    private lateinit var viewModel: CountDownViewModel

    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountDownBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]

        viewModel.startTimer(3)
        displaySeconds()
        isFinished()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun displaySeconds() {
        viewModel.getSeconds().observe(viewLifecycleOwner, Observer {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when (it) {
                    2 -> binding.root.setBackgroundResource(R.drawable.green3)
                    1 -> binding.root.setBackgroundResource(R.drawable.blue2)
                    0 -> binding.root.setBackgroundResource(R.drawable.red1)
                }
            }
            else {
                when (it) {
                    2 -> binding.root.setBackgroundResource(R.drawable.green3_land)
                    1 -> binding.root.setBackgroundResource(R.drawable.blue2_land)
                    0 -> binding.root.setBackgroundResource(R.drawable.red1_land)
                }
            }

            binding.tvSeconds.text = (it.inc()  ).toString()
        })
    }

    private fun isFinished() {
        viewModel.getIsFinished().observe(viewLifecycleOwner, Observer {
            if (it && sharedViewModel.getGame() == "SimonColor") {
                if (sharedViewModel.getDifficulty() == "Easy")
                    Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_simonColorEasyFragment)
                else
                    Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_simonColorHardFragment)
            }
                else
                Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_textColorFragment)
        })
    }
}