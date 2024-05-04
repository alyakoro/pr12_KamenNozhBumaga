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
    private lateinit var user: ImageView
    private lateinit var comp: ImageView

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
        user = view.findViewById(R.id.userView)
        comp = view.findViewById(R.id.compView)

        val controller = findNavController()
        exit.setOnClickListener { controller.navigate(R.id.start2) }

        binding.kBnt.setOnClickListener { userGo(0) }
        binding.bBtn.setOnClickListener { userGo(1) }
        binding.nBtn.setOnClickListener { userGo(2) }
        binding.yBtn.setOnClickListener { userGo(3) }
        binding.sBtn.setOnClickListener { userGo(4) }

        binding.yesBnt.setOnClickListener { controller.navigate(R.id.start2) }
        binding.noBtn.setOnClickListener { controller.navigate(R.id.start2) }
        binding.nyBtn.setOnClickListener { controller.navigate(R.id.start2) }
    }

    private fun userGo(userChoiceId: Int){
        val userChoiceImage = when (userChoiceId) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            3 -> R.drawable.lizard
            4 -> R.drawable.spock
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
            0 -> if (computerChoice == 2 || computerChoice == 3) 1 else if (computerChoice == 1 || computerChoice == 4) 2 else 3
            1 -> if (computerChoice == 0 || computerChoice == 4) 1 else if (computerChoice == 3) 2 else 3
            2 -> if (computerChoice == 1 || computerChoice == 3) 1 else if (computerChoice == 4 || computerChoice == 0) 2 else 3
            3 -> if (computerChoice == 4 || computerChoice == 1) 1 else if (computerChoice == 0 || computerChoice == 2) 2 else 3
            4 -> if (computerChoice == 0 || computerChoice == 2) 1 else if (computerChoice == 1 || computerChoice == 3) 2 else 3
            else -> throw IllegalArgumentException("Некорректный выбор")
        }

        if (result == 1) {
            Toast.makeText(requireContext(),
                "Вы выиграли!", Toast.LENGTH_SHORT).show()
            binding.nyBtn.isEnabled = false
            binding.yesBnt.isEnabled = true
            binding.noBtn.isEnabled = false
        } else if (result == 2) {
            Toast.makeText(requireContext(),
                "Вы проиграли!", Toast.LENGTH_SHORT).show()
            binding.nyBtn.isEnabled = false
            binding.noBtn.isEnabled = true
            binding.yesBnt.isEnabled = false
        } else {
            Toast.makeText(requireContext(),
                "Ничья", Toast.LENGTH_SHORT).show()
            binding.nyBtn.isEnabled = true
            binding.noBtn.isEnabled = false
            binding.yesBnt.isEnabled = false
        }
    }
}