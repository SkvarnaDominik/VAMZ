package com.example.semestralnapraca_skvarna.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.databinding.FragmentTextColorBinding
import com.example.semestralnapraca_skvarna.view_model.SharedViewModel
import com.example.semestralnapraca_skvarna.view_model.TextColorViewModel

class TextColorFragment : Fragment(R.layout.fragment_text_color) {

    private var _binding: FragmentTextColorBinding? = null
    private val binding get() = _binding!! //Slúži pre ľahsie pristupovanie k častiam layoutu (TextView, Button, ImageView)

    private lateinit var viewModel: TextColorViewModel //Slúži na pracovanie s dátami. Tie oddeľuje od fragmentu, ktorý by mal spracovať iba veci, ktoré sa týkajú UI
    private val sharedViewModel: SharedViewModel by activityViewModels() //Zdieľaný viewModel medzi viacerými fragmentmi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextColorBinding.inflate(inflater, container, false) //Nastavenie hodnôt premenným
        viewModel = ViewModelProvider(this)[TextColorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYellow.setTextColor(Color.BLACK) //Nastavenie farby textu v textView
        binding.btnBlue.setTextColor(Color.BLACK)
        binding.btnRed.setTextColor(Color.BLACK)
        binding.btnGreen.setTextColor(Color.BLACK)

        setOnClickBtnYellow() //ClickListener pre stlačenie tlačidla
        setOnClickBtnBlue()
        setOnClickBtnRed()
        setOnClickBtnGreen()
        setOnClickBackToMenu()

        timerSetup() //Metóda pre výber dĺžky Timer-u
        displaySeconds() //Zobrazenie sekúnd
        isFinished() //Kontrola skončenia Timer-u
        roundSetup() //Metóda pre nastavenie daného kola
    }

    private fun timerSetup() { //Metóda pre výber dĺžky Timer-u
        if (sharedViewModel.getDifficulty() == "Easy") { //Ak je zvolený mód Easy, odpočítavať sa bude od 5 sekúnd
          viewModel.startTimer(5) //Nastavenie dĺžky timer-u na 5 sekund
          return
        }
        viewModel.startTimer(3) //Ak je zvolený mód Hard, odpočítavať sa bude od 3 sekúnd
    }

    private fun roundSetup() { //Metóda pre nastavenie daného kola
        viewModel.shuffleModes() //Metóda pre zamiešanie poľa s módamy výberu tlačidla
        viewModel.setMode(viewModel.getModes()[0]) //Nastavenie módu výberu tlačidla pre dané kolo
        binding.tvMode.text = viewModel.getMode() //Vypísanie výberu módu tlačidla do textView tvMode

        viewModel.shuffleTextColors() //Metóda pre zamiešanie poľa s textovou reprezentaciou farieb, ktoré reprezentujú text v tlačidlách
        viewModel.setColor(viewModel.getTextColors()[0]) //Nastavenie vybranej farby pre dané kolo
        binding.tvColor.text = viewModel.getTextColor() //Vypísanie výberu farby do textView tvColor

        viewModel.shuffleTextColors() //Metóda pre zamiešanie poľa s textovou reprezentaciou farieb, ktoré reprezentujú text v tlačidlách
        viewModel.shuffleColorButtons() //Metóda pre zamiešanie poľa s pozadiami tlačidiel, ktoré reprezentujú ich farbu

        setupColorButtons() //Nastavenie pozadí tlačidlám poďľa ich zamiešania

        viewModel.setWinningColorButton(determinateWinningButton()) //Zistenie a zapísanie výherného tlačidla pre dané kolo do zdieľaného viewModel-u
    }
   private fun game(pColorButtonNumber:Int) { //Metóda logiky hry
            isSameAsChosen(determinateWinningButton(), pColorButtonNumber) //Metóda pre zistenie, či používateľ stlačil výherné tlaćidlo
            if (viewModel.getIsSame()) { //Podmienka, ktorá vráti pravdu, ak sa výherné tlaćidlo rovná s použivateľovým vstupov
                viewModel.restartTimer() //Resetovanie časovača na začiatočný čas (Easy = 5 sekund, Hard = 3 sekundy)
                sharedViewModel.addScore() //Navýšenie skóre
                binding.tvScore.text = sharedViewModel.getScore().toString() //Výpis skóre do textView tvScore
                roundSetup() //Metóda pre nastavenie daného kola
            }
            else {
                Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_gameOverviewFragment)
            }
    }

    private fun determinateWinningButton(): Int { //Metóda pre zistenie výherného tlačidla
        if (viewModel.getMode() == "Text") { //Podmienka pre zistenie herného módu daného kola
            for (index in 0 until viewModel.getTextColors().size) {
                if (viewModel.getTextColors()[index] == viewModel.getTextColor()) //Porovnávanie poľa textu farieb, kým sa nenájde index, ktorého hodnota sa rovná vybranej farbe
                    return index //Vrátenie výherného indexu
            }
        }
       else {
           when (viewModel.getTextColor()) { //Switch pre textové farby
               "Yellow" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165328") //Toto je (R.drawable.btn_yellow).toString()
                                                                                          //Porovnávanie poľa pozadí tlačidiel, až kým sa nenájde index, ktorého hodnota sa rovná vybranej farbe
                           return index //Vrátenie výherného indexu
                   }
               }

               "Blue" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165308") //Toto je (R.drawable.btn_blue).toString()
                           return index
                   }
               }

               "Red" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165325") //Toto je (R.drawable.btn_red).toString()
                           return index
                   }
               }

               "Green" -> {
                   for (index in 0 until viewModel.getColorButtons().size) {
                       if (viewModel.getColorButtons()[index].toString() == "2131165318") //Toto je (R.drawable.btn_green).toString()
                           return index
                   }
               }
           }
        }
        return -1 //V prípade chyby sa vráti -1
    }

    private fun setupColorButtons() { //Nastavenie pozadí tlačidlám poďľa ich zamiešania
        binding.btnYellow.setBackgroundResource(viewModel.getColorButtons()[0]) //Nastavenie pozadia tlačidla na n-tý prvok z poľa pozadí tlačidiel
        binding.btnYellow.text = viewModel.getTextColors()[0] //Nastavenie textu tlačidla na n-tý prvok z poľa textu farieb
        binding.btnBlue.setBackgroundResource(viewModel.getColorButtons()[1])
        binding.btnBlue.text = viewModel.getTextColors()[1]
        binding.btnRed.setBackgroundResource(viewModel.getColorButtons()[2])
        binding.btnRed.text = viewModel.getTextColors()[2]
        binding.btnGreen.setBackgroundResource(viewModel.getColorButtons()[3])
        binding.btnGreen.text = viewModel.getTextColors()[3]
    }

    private fun isSameAsChosen(pWinningButtonNumber: Int,pColorButtonNumber: Int) { //Metóda pre zistenie, či používateľ stlačil výherné tlaćidlo
        if (pWinningButtonNumber != pColorButtonNumber)
            viewModel.setIsSame(false) //Ak sa použivateľov vstup nerovna s výherným tlačidlom, nastavý sa flag o rovnosti na false
    }

    private fun displaySeconds() { //Zobrazenie sekúnd
        viewModel.getSeconds().observe(viewLifecycleOwner, Observer { //Observer sleduje liveData. Pokiaľ sa dáta zmenia vykoná svoje telo
            binding.tvSeconds.text = (it.inc()  ).toString() //Vypísanie sekúnd do textView tvSeconds
        })
    }

    private fun isFinished() { //Kontrola skončenia Timer-u
        viewModel.getIsFinished().observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_gameOverviewFragment) //Navigovanie sa do fragmentu GameOverViewFragment
        })
    }

   private fun setOnClickBtnYellow() { //ClickListener pre stlačenie tlačidla
        binding.btnYellow.setOnClickListener() {
           game(0 ) //Poslanie číselnej reprezentácie tlačidla do metódy game
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
            Navigation.findNavController(binding.root).navigate(R.id.action_textColorFragment_to_mainMenuFragment) //Navigovanie sa do fragmentu GameOverViewFragment
        }
    }
}