package com.korobeynikova.pr12_kamennozhbumaga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.korobeynikova.pr12_kamennozhbumaga.databinding.FragmentStartBinding
import kotlin.random.Random

class Start : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exit = view.findViewById<ImageView>(R.id.exitBtn)

        val controller = findNavController()
        exit.setOnClickListener { controller.navigate(R.id.game) }

        binding.button.setOnClickListener { controller.navigate(R.id.game) }
        binding.button2.setOnClickListener { controller.navigate(R.id.rules) }
        }
}