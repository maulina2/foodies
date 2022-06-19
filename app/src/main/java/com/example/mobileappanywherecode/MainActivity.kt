package com.example.mobileappanywherecode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.window.layout.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator
import com.example.mobileappanywherecode.databinding.ActivityMainBinding

lateinit var metrics: WindowMetrics

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
}