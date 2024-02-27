package com.example.mvpexample.view.simpson.simpsonDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpexample.databinding.FragmentSimpsonDetailBinding
import com.squareup.picasso.Picasso

class SimpsonDetailFragment : Fragment() {

    private lateinit var binding: FragmentSimpsonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimpsonDetailBinding.inflate(inflater, container, false)

//        val bundle: SimpsonDetailFragmentArgs by navArgs()
        val args = arguments
        val simpsonCharacterImage = args?.getString(args_image)
        val simpsonCharacterName = args?.getString(args_name)

        Picasso.get().load(simpsonCharacterImage).into(binding.ivDetailCharacter)
        binding.tvDetailCharacterName.text = simpsonCharacterName

        return binding.root
    }
    companion object {
        private const val args_name = "name"
        private const val args_image = "image"

        fun newInstance(name: String, image: String): SimpsonDetailFragment {
            val fragment = SimpsonDetailFragment()
            val args = Bundle()
            args.putString(args_name, name)
            args.putString(args_image, image)
            fragment.arguments = args
            return fragment
        }
    }

}