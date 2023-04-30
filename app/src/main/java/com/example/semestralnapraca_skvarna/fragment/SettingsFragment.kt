package com.example.semestralnapraca_skvarna.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentSettingsBinding
import com.example.semestralnapraca_skvarna.view_model.SettingsViewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!! //binding pre lahsie pristupovanie
    private lateinit var viewModel: SettingsViewModel //prepojenie Settings s SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        binding.tvUsername.text = viewModel.getNewUsername()
        binding.ivProfilePicture.setImageResource(viewModel.getProfilePictureResources()[viewModel.getIndexOfProfilePicture()])
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDarkLightMode(binding.root)
        changeUserName()
        changeProfilePicture()
    }

    private fun setDarkLightMode(view: View) {
        binding.swDarkMode.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
            if (binding.swDarkMode.isChecked && viewModel.getIsDarkMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
    }

    private fun changeUserName() {
        val button = binding.btnChangeUserName
        button.setOnClickListener() {
            if (binding.etUserName.text.toString().length > 10) {
                Toast.makeText(activity, "Username can be long only 10 characters", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etUserName.text.toString().isEmpty()) {
                Toast.makeText(activity, "Username can't be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.setNewUsername(binding.etUserName.text.toString())
                binding.tvUsername.text = viewModel.getNewUsername()
                Toast.makeText(activity, "Username was changed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeProfilePicture() {
        binding.ivProfilePicture.setOnClickListener {
            if (viewModel.getIndexOfProfilePicture() == viewModel.getProfilePictureResources().size)
                viewModel.setIndexOfProfilePicture(0)
            else {
                binding.ivProfilePicture.setImageResource(viewModel.getProfilePictureResources()[viewModel.getIndexOfProfilePicture()])
                viewModel.setIndexOfProfilePicture(viewModel.getIndexOfProfilePicture() + 1)
            }
            Toast.makeText(activity, "Profile picture was changed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}