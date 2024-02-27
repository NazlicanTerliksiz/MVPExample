package com.example.mvpexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpexample.R
import com.example.mvpexample.databinding.ActivityMainBinding
import com.example.mvpexample.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")

        val bundle = Bundle().apply {
            putString("name", name)
        }

        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintLayout, homeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}