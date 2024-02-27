package com.example.mvpexample.view.home

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpexample.adapters.ViewPagerAdapter
import com.example.mvpexample.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        val name = arguments?.getString("name")
        binding.welcomeTv.text = String.format("Welcome %s", name)

        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){ tabLayout, index ->
            tabLayout.text = when(index){
                0-> {"Simpson"}
                1-> {"Rick And Morty"}
                else -> {throw Resources.NotFoundException("Position not found")}
            }
        }.attach()

        return binding.root
    }

}