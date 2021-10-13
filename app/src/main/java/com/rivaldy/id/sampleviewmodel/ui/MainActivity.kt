package com.rivaldy.id.sampleviewmodel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rivaldy.id.sampleviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
    }

    private fun initObservers() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.loadUserData().observe(this, {
            binding.resultTV.text = it
        })
    }
}