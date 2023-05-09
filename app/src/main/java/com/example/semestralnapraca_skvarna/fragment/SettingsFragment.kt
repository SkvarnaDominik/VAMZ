package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSettingsBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.tvUsername.text = sharedViewModel.getUsername()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVariables() //Metóda slúžiaca na nastavenie mena profilovej fotografie užívateľa pri vytvorení fragmentu
        changeUserName() //Metóda pre zmenu mena používateľa
        changeProfilePicture() //Metóda pre zmenu profilovej fotografie používateľa
        setOnClickBackToMenu() //ClickListener pre stlačenie tlačidla

    }

    private fun setVariables() {
        binding.ivProfilePicture.setImageResource(sharedViewModel.getProfilePictureResources()[sharedViewModel.getIndexOfProfilePicture()]) //Nastavenie profilovej fotky
                                                                                                                                            //do imageView ivProfilePicture
                                                                                                                                            //pri vytvorení fragmentu
        binding.tvUsername.text = sharedViewModel.getUsername() //Nastavenie mena užívateľa do textView tvUsername pri vytvorení fragmentu
    }

    private fun changeUserName() { //Metóda pre zmenu mena používateľa
        binding.btnChangeUserName.setOnClickListener() {
            if (binding.etUserName.text.toString().length > 10) { //Podmienka pre dĺžku mena použivateľa
                Toast.makeText(activity, "Username cannot be longer than 10 characters", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
            }
            else if (binding.etUserName.text.toString().isEmpty()) { //Podmienka pre dĺžku mena použivateľa
                Toast.makeText(activity, "Username cannot be empty", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
            }
            else {
                sharedViewModel.setUsername(binding.etUserName.text.toString()) //Nastavenie mena používateľa v zdieľanom viewModel-y
                binding.tvUsername.text = sharedViewModel.getUsername() //Výpis mena používateľa do textView tvUsername
                Toast.makeText(activity, "Username was changed", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
            }
        }
    }

    private fun changeProfilePicture() { //Metóda pre zmenu profilovej fotografie používateľa
        binding.ivProfilePicture.setOnClickListener {
            sharedViewModel.setIndexOfProfilePicture(sharedViewModel.getIndexOfProfilePicture() + 1) //Nastavenie profilovej fotografie používateľa v zdieľanom viewModel-y
            if (sharedViewModel.getIndexOfProfilePicture() == sharedViewModel.getProfilePictureResources().size) //Podmienka pre zistenie konca poľa
                sharedViewModel.setIndexOfProfilePicture(0) //Nastavenie indexu na 0
            binding.ivProfilePicture.setImageResource(sharedViewModel.getProfilePictureResources()[sharedViewModel.getIndexOfProfilePicture()]) //Výpis fotografie do imageView ivProfilePicture
            Toast.makeText(activity, "Profile picture was changed", Toast.LENGTH_SHORT).show() //Výpis krátkej správy na obrazovku
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_settingsFragment_to_mainMenuFragment) //Navigovanie sa na fragment MainMenuFragment
        }
    }
}