package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSettingsBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.tvUsername.text = sharedViewModel.getUsername()
        binding.ivProfilePicture.setImageResource(sharedViewModel.getProfilePictureResources()[sharedViewModel.getIndexOfProfilePicture()])
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVariables()
        setDarkLightMode(binding.root)
        changeUserName()
        changeProfilePicture()
        setOnClickBackToMenu()

    }

    private fun setVariables() {
        binding.tvUsername.text = sharedViewModel.getUsername()
        binding.ivProfilePicture.setImageResource(sharedViewModel.getProfilePictureResources()[sharedViewModel.getIndexOfProfilePicture()])
    }

    private fun setDarkLightMode(view: View) {
        binding.swDarkMode.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
            if (binding.swDarkMode.isChecked && sharedViewModel.getIsDarkMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
    }

    private fun changeUserName() {
        binding.btnChangeUserName.setOnClickListener() {
            if (binding.etUserName.text.toString().length > 10) {
                Toast.makeText(activity, "Meno nemôže obsahovať viac ako 10 písmen", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etUserName.text.toString().isEmpty()) {
                Toast.makeText(activity, "Meno nesmie byť prázdne", Toast.LENGTH_SHORT).show()
            }
            else {
                sharedViewModel.setUsername(binding.etUserName.text.toString())
                binding.tvUsername.text = sharedViewModel.getUsername()
                Toast.makeText(activity, "Meno sa zmenilo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeProfilePicture() {
        binding.ivProfilePicture.setOnClickListener {
            sharedViewModel.setIndexOfProfilePicture(sharedViewModel.getIndexOfProfilePicture() + 1)
            if (sharedViewModel.getIndexOfProfilePicture() == sharedViewModel.getProfilePictureResources().size)
                sharedViewModel.setIndexOfProfilePicture(0)
            binding.ivProfilePicture.setImageResource(sharedViewModel.getProfilePictureResources()[sharedViewModel.getIndexOfProfilePicture()])
            Toast.makeText(activity, "Fotka sa zmenila", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnClickBackToMenu() {
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_settingsFragment_to_mainMenuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}