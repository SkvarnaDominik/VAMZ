package com.example.semestralnapraca_skvarna.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentMainMenuBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        sharedViewModel.getGame().observe(viewLifecycleOwner) {}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickSimonColor()
        setOnClickTextColor()
        setOnClickScore()
        setOnClickSettings()

    }

    private fun setOnClickSimonColor () {
        binding.btnSimonColor.setOnClickListener() {
            /*val bundle = Bundle()
            bundle.putString("destination", "SimonColor")
            val fragment = CountDownFragment()
            fragment.arguments = bundle*/

            //val action = MainMenuFragmentDirections.actionMainMenuToCountDownFragment("SimonColor")
            //parentFragmentManager.beginTransaction().apply { replace(R.id.mainMenuLayout, CountDownFragment()).commit() }
            /*findNavController().navigate(R.id.action_mainMenu_to_countDownFragment, Bundle().apply {
               putString("destination", "SimonColor")
            })*/

            /*val direction = MainMenuFragmentDirections.actionMainMenuToCountDownFragment(0)
            findNavController().navigate(direction)*/
            sharedViewModel.setGame("SimonColor")
            sharedViewModel.getGame().observe(viewLifecycleOwner,Observer{
               Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_simonColorDifficultyFragment)
            })
        }
    }

    private fun setOnClickTextColor () {
        binding.btnTextColor.setOnClickListener() {
            sharedViewModel.setGame("TextColor")
            sharedViewModel.getGame().observe(viewLifecycleOwner,Observer{
                Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_textColorDifficultyFragment)
            })

        }
    }

    private fun setOnClickScore () {
        binding.btnScore.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenuFragment_to_scoreFragment)
        }
    }

    private fun setOnClickSettings () {
        binding.btnSettings.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainMenu_to_settings)
        }
    }
}

