package com.example.mvpexample.view.RickAndMorty.rickAndMortyDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpexample.databinding.FragmentRickAndMortyDetailBinding
import com.squareup.picasso.Picasso

class RickAndMortyDetailFragment : Fragment() {

    private lateinit var binding: FragmentRickAndMortyDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRickAndMortyDetailBinding.inflate(inflater, container, false)

        val args = arguments
        val characterImage = args?.getString("characterImage")
        val characterName = args?.getString("characterName")

        Picasso.get().load(characterImage).into(binding.ivDetailCharacter)
        binding.tvDetailCharacterName.text = characterName

        return binding.root
    }
}