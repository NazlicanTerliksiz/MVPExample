package com.example.mvpexample.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpexample.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)

        binding.continueBtn.setOnClickListener {

            val name = binding.nameEt.text.toString()

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }
}