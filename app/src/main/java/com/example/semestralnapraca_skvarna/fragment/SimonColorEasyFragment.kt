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
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorEasyBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.SimonColorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonColorEasyFragment : Fragment(R.layout.fragment_simon_color_easy) {

    private var _binding: FragmentSimonColorEasyBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private lateinit var viewModel: SimonColorViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private val coroutineScope = CoroutineScope(Dispatchers.IO) //Korutina pre spustenie danej metódy v ďalšom vlákne mimo behu hlavného kódu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSimonColorEasyBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným
        viewModel = ViewModelProvider(this)[SimonColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedViewModel.getIsFirstRound()) { //Podmienka pre zistenie či sa jedná o prvé kolo
            viewModel.pickRandomColorButton("Easy") //Metóda pre výber náhodného tlačidla
            coroutineScope.launch { //Spustenie metódy v inom vlákne ako beží hlavný kód
                playSequence() //Metóda pre zobrazenie postupnoti výberu tlačidiel
            }
        }

        setOnClickBtnRed() //ClickListener pre stlačenie tlačidla
        setOnClickBtnBlue()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        sharedViewModel.setIsFirstRound(false) //Nastavenie flag-u či bola vybraná obtiažnosť na nepravdu
    }

    private fun game(pColorButtonNumber:Int) { //Metóda logiky hry
        viewModel.addToUserSequence(pColorButtonNumber) //Metóda pre pridanie tlačidla, ktoré stlačil použivateľ do postupnoti vstupov použivateľa
        viewModel.addColorButtonsPressed() //Navýšenie premennej počtu vstupov, ktoré používateľ zadal
        if (viewModel.getColorButtonsPressed() == viewModel.getRound()) { //Podmienka, ktorá vráti pravdu, ak používateľ zadal toľko vstupov, koľké je kolo
            checkUserSequence() //Metóda pre zistenie, či sa sekvencia vstupov od použivateľa rovná sekvencií vybraných tlačidiel počas hry
            if (viewModel.getIsSame()) { //Podmienka, ktorá vráti pravdu, ak sa tieto sekvencie rovnajú
                viewModel.clearUserSequence() //Vyčistenie sekvencie používateľa
                sharedViewModel.addScore() //Navýšenie skóre
                binding.tvScore.text = sharedViewModel.getScore().toString() //Výpis skóre do textView tvScore
                viewModel.setColorButtonsPressed(0) //Zresetovanie počítadla inputov od používateľa
                viewModel.addRound() //Pridanie kola
                viewModel.pickRandomColorButton("Easy") //Metóda pre výber náhodného tlačidla
                coroutineScope.launch { //Spustenie metódy v inom vlákne ako beží hlavný kód
                    playSequence() //Metóda pre zobrazenie postupnoti výberu tlačidiel
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorEasyFragment_to_gameOverviewFragment) //Navigovanie sa do fragmentu GameOvewviewFragment
            }
        }
    }

    private fun checkUserSequence() { //overenie stlačenia správneho tlačidla používateľom
        for (index in 0 until viewModel.getGameSequence().size) {
            if (viewModel.getGameSequence()[index] != viewModel.getUserSequence()[index]) //Porovnávanie sekvencie hry a sekvencie vstupov použivateľa
                viewModel.setIsSame(false) //Pri nerovnosti sa nastavý flag isSame na nepravdu
        }
    }

    private suspend fun playSequence() { //Metóda pre zobrazenie postupnoti výberu tlačidiel
        binding.btnRed.isClickable = false //Znemožnenie stlačenie tlačidla, kým sa zobrazuje postupnosť tlačidiel
        binding.btnBlue.isClickable = false
        binding.btnGreen.isClickable = false
        for (index in 0 until viewModel.getGameSequence().size) {
            when (viewModel.getGameSequence()[index]) { //switch pre vybrané tlačidla
                0 -> {
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red_pressed) //Nastavenie pozadia tlačidla na tmavšie, čím sa značí jeho výber v postupnosti použivateľovi
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red) //Nastavenie pozadia tlačidla na pôvodnú hodnotu
                }
                1 -> {
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue_pressed)
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue)
                }
                2 -> {
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green_pressed)
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green)
                }
            }
        }
        binding.btnRed.isClickable = true //Opätovné sfunkčnenie stlačnia tlačidla
        binding.btnBlue.isClickable = true
        binding.btnGreen.isClickable = true
    }

    private fun setOnClickBtnBlue() { //ClickListener pre stlačenie tlačidla
        binding.btnRed.setOnClickListener() {
            game(0) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBtnRed() { //ClickListener pre stlačenie tlačidla
        binding.btnBlue.setOnClickListener() {
            game(1) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBtnGreen() { //ClickListener pre stlačenie tlačidla
        binding.btnGreen.setOnClickListener() {
            game(2) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorEasyFragment_to_mainMenuFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }
}