package com.example.semestralnapraca_skvarna.fragment

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
    private lateinit var viewModel: CountDownViewModel //prepojenie Settings s SettingsViewModel


    private val sharedViewModel: SharedViewModel by activityViewModels()

    //private val args: CountDownFragmentArgs by navArgs<>()
    //private val args = this.arguments
    //private val destination = requireArguments().getString("destination").toString()
    //private val data = args?.getString("destination").toString()*/

   // private val args:CountDownFragmentArgs by navArgs()
    //private val destination = this.arguments.toString()

   //private val destination = args.destination
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountDownBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]

        //binding.seconds.text = sharedViewModel.getGame().value
        viewModel.startTimer()
        displaySeconds()
        isFinished()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun displaySeconds() {
        viewModel.getSeconds().observe(viewLifecycleOwner, Observer {
            binding.seconds.text = (it.inc()  ).toString()
        })
    }

    private fun isFinished() {
        viewModel.getIsFinished().observe(viewLifecycleOwner, Observer {
            if (it && sharedViewModel.getGame().value == "SimonColor")
                Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_simonColorFragment)
            else
                Navigation.findNavController(binding.root).navigate(R.id.action_countDownFragment_to_textColorFragment)
        })
    }
}