package com.rivaldy.id.sampleviewmodel.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rivaldy.id.sampleviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initClick()
    }

    private fun initObservers() {
        viewModel.loadUserData()
        viewModel.userData.observe(this, {
            binding.resultTV.text = it
        })
    }

    private fun initClick() {
        binding.actionMB.setOnClickListener {
            viewModel.setSampleData()
        }
    }
}