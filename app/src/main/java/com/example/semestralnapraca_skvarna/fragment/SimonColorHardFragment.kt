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
import com.example.semestralnapraca_skvarna.databinding.FragmentSimonColorHardBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.SimonColorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonColorHardFragment : Fragment(R.layout.fragment_simon_color_hard) {

    private var _binding: FragmentSimonColorHardBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private lateinit var viewModel: SimonColorViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    private val coroutineScope = CoroutineScope(Dispatchers.IO) //Korutina pre spustenie danej metódy v ďalšom vlákne mimo behu hlavného kódu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSimonColorHardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SimonColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvScore.text = sharedViewModel.getScore().toString() //Výpis skóre do textView tvScore

        if (sharedViewModel.getIsFirstRound()) { //Podmienka pre zistenie či sa jedná o prvé kolo
            viewModel.pickRandomColorButton("Hard") //Metóda pre výber náhodného tlačidla
            coroutineScope.launch { //Spustenie metódy v inom vlákne ako beží hlavný kód
                playSequence() //Metóda pre zobrazenie postupnoti výberu tlačidiel
            }
        }

        setOnClickBtnYellow() //ClickListener pre stlačenie tlačidla
        setOnClickBtnBlue()
        setOnClickBtnRed()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        sharedViewModel.setIsFirstRound(false) //Nastavenie Flag-u či bola vybraná obtiažnosť na nepravdu
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
                viewModel.setColorButtonsPressed(0)  //Zresetovanie počítadla inputov od používateľa
                viewModel.addRound() //Pridanie kola
                viewModel.pickRandomColorButton("Hard")  //Metóda pre výber náhodného tlačidla
                coroutineScope.launch { //Spustenie metódy v inom vlákne ako beží hlavný kód
                    playSequence() //Metóda pre zobrazenie postupnoti výberu tlačidiel
                }
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_simonColorHardFragment_to_gameOverviewFragment) //Navigovanie sa do fragmentu GameOvewviewFragment
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
        binding.btnYellow.isClickable = false //Znemožnenie stlačenie tlačidla, kým sa zobrazuje postupnosť tlačidiel
        binding.btnBlue.isClickable = false
        binding.btnRed.isClickable = false
        binding.btnGreen.isClickable = false
        for (index in 0 until viewModel.getGameSequence().size) {
            when (viewModel.getGameSequence()[index]) { //switch pre vybrané tlačidla
                0 -> {
                    delay(250) //čakanie 250ms
                    binding.btnYellow.setBackgroundResource(R.drawable.btn_yellow_pressed) //Nastavenie pozadia tlačidla na tmavšie, čím sa značí jeho výber v postupnosti použivateľovi
                    delay(250)
                    binding.btnYellow.setBackgroundResource(R.drawable.btn_yellow) //Nastavenie pozadia tlačidla na pôvodnú hodnotu
                }
                1 -> {
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue_pressed)
                    delay(250)
                    binding.btnBlue.setBackgroundResource(R.drawable.btn_blue)
                }
                2 -> {
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red_pressed)
                    delay(250)
                    binding.btnRed.setBackgroundResource(R.drawable.btn_red)
                }
                3 -> {
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green_pressed)
                    delay(250)
                    binding.btnGreen.setBackgroundResource(R.drawable.btn_green)
                }
            }
        }
        binding.btnYellow.isClickable = true //Opätovné sfunkčnenie stlačnia tlačidla
        binding.btnBlue.isClickable = true
        binding.btnRed.isClickable = true
        binding.btnGreen.isClickable = true
    }

    private fun setOnClickBtnYellow() { //ClickListener pre stlačenie tlačidla
        binding.btnYellow.setOnClickListener() {
           game(0) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBtnBlue() { //ClickListener pre stlačenie tlačidla
        binding.btnBlue.setOnClickListener() {
           game(1) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBtnRed() { //ClickListener pre stlačenie tlačidla
        binding.btnRed.setOnClickListener() {
            game(2) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBtnGreen() { //ClickListener pre stlačenie tlačidla
        binding.btnGreen.setOnClickListener() {
            game(3) //Poslanie číselnej reprezentácie tlačidla do metódy game
        }
    }

    private fun setOnClickBackToMenu() { //ClickListener pre stlačenie tlačidla
        binding.btnBackToMenu.setOnClickListener() {
            sharedViewModel.resetScore() //Resetovanie skóre po odohraní a zapísaní hry do databáz
            sharedViewModel.setIsDifficultyChosen(false) //Resetovanie výberu odbtiažnosti
            sharedViewModel.setIsFirstRound(false) //Nastavenie Flag-u či sa jedná o prvé kolo na nepravdu
            Navigation.findNavController(binding.root).navigate(R.id.action_simonColorHardFragment_to_mainMenuFragment) //Navigovanie sa do fragmentu MainMenuFragment
        }
    }
}