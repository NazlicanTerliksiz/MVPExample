package com.example.mvpexample.view.RickAndMorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.databinding.FragmentHarryPotherBinding

class RickAndMortyFragment : Fragment(), RickAndMortyContract.RickAndMortyViewContract {

    private lateinit var binding: FragmentHarryPotherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHarryPotherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setRickAndMortyCharacters(charactersList: List<RickAndMortyModel>) {
        TODO("Not yet implemented")
    }

    override fun errorShowSnackBar(message: String) {
        TODO("Not yet implemented")
    }

    override fun failShowSnackBar(message: String) {
        TODO("Not yet implemented")
    }
}