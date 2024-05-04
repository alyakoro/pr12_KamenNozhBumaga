package com.korobeynikova.pr12_kamennozhbumaga

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.korobeynikova.pr12_kamennozhbumaga.databinding.FragmentGameBinding
import kotlin.random.Random

class Game : Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yesBnt.isEnabled = false
        binding.noBtn.isEnabled = false

        val exit = view.findViewById<ImageView>(R.id.exitBtn)
        val user = view.findViewById<ImageView>(R.id.userView)
        val comp = view.findViewById<ImageView>(R.id.compView)

        val controller = findNavController()
        exit.setOnClickListener { controller.navigate(R.id.start2) }

        val userChoiceId = view.id
        val userChoiceImage = when (userChoiceId) {
            R.id.kBnt -> R.drawable.rock
            R.id.bBtn -> R.drawable.paper
            R.id.nBtn -> R.drawable.scissors
            R.id.yBtn -> R.drawable.lizard
            R.id.sBtn -> R.drawable.spock
            else -> throw IllegalArgumentException("Некорректный выбор")
        }
        user.setImageResource(userChoiceImage)

        val computerChoiceId = Random.nextInt(5)
        val computerChoiceImageRes = when (computerChoiceId) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            3 -> R.drawable.lizard
            4 -> R.drawable.spock
            else -> throw IllegalStateException("Некорректный случайный выбор")
        }
        comp.setImageResource(computerChoiceImageRes)

        determineWinner(userChoiceId, computerChoiceId)
    }

    private fun determineWinner(userChoice: Int, computerChoice: Int) {
        val result = when (userChoice) {
            R.id.kBnt -> if (computerChoice == 2 || computerChoice == 3) 1 else if (computerChoice == 1 || computerChoice == 4) 2 else 3
            R.id.bBtn -> if (computerChoice == 0 || computerChoice == 4) 1 else if (computerChoice == 3) 2 else 3
            R.id.nBtn -> if (computerChoice == 1 || computerChoice == 3) 1 else if (computerChoice == 4 || computerChoice == 0) 2 else 3
            R.id.yBtn -> if (computerChoice == 4 || computerChoice == 1) 1 else if (computerChoice == 0 || computerChoice == 2) 2 else 3
            R.id.sBtn -> if (computerChoice == 0 || computerChoice == 2) 1 else if (computerChoice == 1 || computerChoice == 3) 2 else 3
            else -> throw IllegalArgumentException("Некорректный выбор")
        }

        if (result == 1) {binding.yesBnt.isEnabled = true} else if (result == 2) {binding.noBtn.isEnabled = true} else {
            Toast.makeText(requireContext(),
                "Ничья", Toast.LENGTH_SHORT).show()
            val controller = findNavController()
            controller.navigate(R.id.start2)
        }
    }
}