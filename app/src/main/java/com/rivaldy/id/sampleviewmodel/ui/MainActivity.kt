package com.rivaldy.id.sampleviewmodel.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rivaldy.id.sampleviewmodel.data.network.ApiClient
import com.rivaldy.id.sampleviewmodel.data.repository.MovieRepository
import com.rivaldy.id.sampleviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initClick()
    }

    private fun initObservers() {
        // Not recommended, but this is just example (best step using DI)
        val apiClient = ApiClient()
        val repository = MovieRepository(apiClient)
        val factory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.loading.observe(this, {
            binding.loadingPB.visibility = if (it == true) View.VISIBLE else View.GONE
        })
        viewModel.getMovies.observe(this, {
            val listData = mutableListOf<String>()
            it.map { movie -> listData.add(movie.title.toString()) }
            binding.resultTV.text = listData.joinToString()
        })
    }

    private fun initClick() {
        binding.actionMB.setOnClickListener {
            viewModel.getMovies()
        }
    }
}